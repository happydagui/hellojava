package hello.service;

import hello.dao.OrderDao;
import hello.dao.OrderSequenceDao;
import hello.meta.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by min on 17-1-10.
 */
@Service
public class OrderService {
    @Resource
    private OrderSequenceDao orderSequenceDao;

    @Resource
    private OrderDao orderDao;


    final int SHADE = 3;

    @Transactional
    public void createOrder(Order order) {
        if (order == null || order.getUserId() < 0) {
            throw new IllegalArgumentException("Wrong order!");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("key", "order_sequence_" + (order.getUserId() % SHADE + 1));

        int nextId = orderSequenceDao.nextval(params);
        order.setOrderId(nextId * SHADE + order.getUserId() % SHADE);
        System.out.println("userid#" + order.getUserId() + ", orderid#" + order.getOrderId());
        orderDao.createOrder(order);
    }

    public Order getOrder(int orderId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderId", orderId);
        params.put("index", (orderId % SHADE + 1));
        return orderDao.getOrder(params);
    }
}
