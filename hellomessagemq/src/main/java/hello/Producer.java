package hello;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by min on 17-1-11.
 */
public class Producer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin",
                "admin", ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        try {
            // 会自动重试
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("hello");

            MessageProducer producer = session.createProducer(destination);

            for (int i = 0; i < 10; i++) {
                TextMessage message = session.createTextMessage("MESSAGE " + i);
                producer.send(message);
            }
            session.commit();
            session.close();
            connection.close(); // 否则程序一直挂起
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
