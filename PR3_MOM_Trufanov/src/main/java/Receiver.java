import javax.jms.*;
import javax.naming.*;

public class Receiver implements MessageListener {
    public void startListener() throws JMSException, NamingException {
        InitialContext c = new InitialContext();
        QueueConnectionFactory con = (QueueConnectionFactory) c.lookup ("queue/connectionFactory");
        QueueConnection connect = con.createQueueConnection();
        QueueSession s = connect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = s.createQueue("mom.queue");
        MessageConsumer consumer = s.createConsumer(queue);
        consumer.setMessageListener(this);
        connect.close();
    }

    @Override
    public void onMessage (Message message) {
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            try {
                System.out.printf("Сообщение: " + msg.getText());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }
}