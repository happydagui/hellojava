package hello.spring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by min on 17-1-11.
 */
@Component("testQueueReceiver1")
public class QueueReceiver1 implements MessageListener {
    public void onMessage(Message message) {
        try {
            System.out.println("QueueReceiver1: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
