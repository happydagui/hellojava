package hello.spring;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by min on 17-1-11.
 */
@Component("queueSender")
public class QueueSender {

    @Resource(name = "jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    public void send(String queue, final String message) {
        jmsTemplate.send(queue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    /*
     * 结合 jmsTemplate 的 defaultDestination 属性使用，需要传入 destination ，使用默认的消息转换器发送
     * @param message
     */
    public void send(final String message) {
        jmsTemplate.convertAndSend(message);
    }
}
