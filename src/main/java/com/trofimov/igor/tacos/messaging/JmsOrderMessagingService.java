package com.trofimov.igor.tacos.messaging;

import com.trofimov.igor.tacos.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
@AllArgsConstructor
public class JmsOrderMessagingService implements OrderMessagingService {

    private JmsTemplate jmsTemplate;
    private Destination orderQueue;

    @Override
    public void sendOrder(Order order) {
        jmsTemplate.convertAndSend("tacocloud.order.queue", order, message -> {
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }

/*    @Override
    public void sendOrder(Order order) {
        jmsTemplate.send(orderQueue, session -> {
            Message message = session.createObjectMessage(order);
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }*/

/*    @Override
    public void sendOrder(Order order) {
        jmsTemplate.send("tacocloud.order.queue",
                session -> session.createObjectMessage(order));
    }*/

}
