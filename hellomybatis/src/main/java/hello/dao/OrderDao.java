/**
 * Created by min on 17-1-10.
 */
package hello.dao;

import hello.meta.Order;

import java.util.Map;

public interface OrderDao {
    Order getOrder(Map<String, Object> map);  // s

    void createOrder(Order order);
}
