package hello;

import hello.meta.Order;
import hello.service.OrderService;
import hello.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by min on 17-1-10.
 * files:
 * classpath:mybatis-config.xml
 * classpath:mapper/User.xml
 * classpath: hello.meta.User
 * classpath: hello/dao/UserDao      # interface
 * + classpath: spring-config.xml
 * + classpath: db.properties
 * + classpath: hell0/service/UserService
 */
public class MainWithSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        UserService userService = context.getBean(UserService.class);
        System.out.print(userService.getAllUser());

        OrderService orderService = context.getBean(OrderService.class);
//        orderService.createOrder(new Order(1)); // 4
//        orderService.createOrder(new Order(2)); // 5
//        orderService.createOrder(new Order(2)); // 8
//        orderService.createOrder(new Order(2));  // 11
//        orderService.createOrder(new Order(3));  // 3
//        orderService.createOrder(new Order(3));  // 6
        System.out.println(orderService.getOrder(3));
        System.out.println(orderService.getOrder(4));
        System.out.println(orderService.getOrder(8));
        System.out.println(orderService.getOrder(11));


    }
}
