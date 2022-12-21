import javax.jms.*;
import javax.naming.*;

public class Sender {
    private final MessageProducer producer;
    private final Session session;
    private final QueueConnection connect;

    public Sender() throws JMSException, NamingException {
        InitialContext context = new InitialContext();
        QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup ("queue/connectionFactory");
        connect = factory.createQueueConnection();
        QueueSession session = connect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        this.session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("mom.queue");
        this.producer = session.createProducer(queue);
    }

    public void print(String message) throws JMSException {
        System.out.printf("Добавленное сообщение: " + message);
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);
        connect.close();
    }
}