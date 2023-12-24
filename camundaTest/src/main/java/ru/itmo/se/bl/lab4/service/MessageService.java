package ru.itmo.se.bl.lab4.service;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.fusesource.stomp.jms.StompJmsDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.Serializable;

@Service
public class MessageService {
    private final StompJmsConnectionFactory factory;

    @Autowired
    public MessageService(StompJmsConnectionFactory factory) {
        this.factory = factory;
    }

    public void sendObjectMessage(String queueName, Serializable object) throws JMSException {
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = new StompJmsDestination(queueName);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        ObjectMessage message = session.createObjectMessage();
        message.setObject(object);

        producer.send(message);
        session.close();
        connection.close();
    }
}
