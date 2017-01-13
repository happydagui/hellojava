package hello;

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
        UserService userService = (UserService) context.getBean(UserService.class);
        System.out.print(userService.getAllUser());
    }
}
