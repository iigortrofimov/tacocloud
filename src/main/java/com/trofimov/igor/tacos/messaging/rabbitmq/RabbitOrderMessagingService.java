package com.trofimov.igor.tacos.messaging.rabbitmq;

import com.trofimov.igor.tacos.domain.Order;
import com.trofimov.igor.tacos.messaging.OrderMessagingService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitOrderMessagingService implements OrderMessagingService {

    private RabbitTemplate rabbit;

    @Override
    public void sendOrder(Order order) {
        rabbit.convertAndSend("tacocloud.order", order, message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setHeader("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }
/*
    @Override
    public void sendOrder(Order order) {
        rabbit.convertAndSend("tacocloud.order", order);
    }
*/
/*
    @Override
    public void sendOrder(Order order) {
        MessageConverter messageConverter = rabbit.getMessageConverter();
        MessageProperties properties = new MessageProperties();
        properties.setHeader("X_ORDER_SOURCE", "WEB");
        Message message = messageConverter.toMessage(order, properties);
        rabbit.send("tacocloud.order",message);
    }
*/
}
