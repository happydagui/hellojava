package hello;


import javax.jms.*;

/**
 * Created by min on 17-1-11.
 */
public class Consumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory("admin", "admin",
                org.apache.activemq.ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // 第一个参数是 false ，读取消息后删除
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("hello");

            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                // 当没有消息的时候，这一句似乎会挂起当前线程
                TextMessage message = (TextMessage) consumer.receive();
                if (message != null) {
                    System.out.println(message.getText());
                } else {
                    // 这一句好像从不会执行，一直在等待
                    consumer.close();
                    System.out.println("no more message");
                    break;
                }
            }
//            session.commit();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
