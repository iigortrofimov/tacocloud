package com.trofimov.igor.tacos.messaging.rabbitmq;

import com.trofimov.igor.tacos.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitOrderReceiver {

    private RabbitTemplate rabbit;
    private MessageConverter converter;

    public Order receiveOrder() {
        return rabbit.receiveAndConvert("tacocloud.orders", new ParameterizedTypeReference<Order>() {
        });

    }
/*
    public Order receiveOrder() {
        return (Order) rabbit.receiveAndConvert("tacocloud.orders");

    }
*/
/*
    public Order receiveOrder() {
        Message message = rabbit.receive("tacocloud.orders");
        return message != null ? (Order) converter.fromMessage(message) : null;

    }
*/

}
