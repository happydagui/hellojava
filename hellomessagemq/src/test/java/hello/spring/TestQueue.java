package hello.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by min on 17-1-11.
 * 真实环境下监听的需要 servlet 容器来运行，本次运行完成后就退出了
 * 在 web.xml 中使用全局 contextConfigLocation 来加载，记得
 * <listener>
 *   <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 * </listener>
 */

public class TestQueue {

    private ApplicationContext context;
    private final String QUEUE = "hello";
    private QueueSender sender;

    {
        context = new ClassPathXmlApplicationContext("spring-activemq.xml");
        sender = context.getBean(QueueSender.class);
    }

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            sender.send(QUEUE, "hi, message#" + i);
        }
    }
}
