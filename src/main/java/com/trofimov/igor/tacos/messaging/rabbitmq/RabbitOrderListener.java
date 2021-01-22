package com.trofimov.igor.tacos.messaging.rabbitmq;

import com.trofimov.igor.tacos.domain.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RabbitOrderListener {

    private RabbitTemplate rabbit;
    private MessageConverter converter;

    @RabbitListener(queues = "tacocloud.orders")
    public Order receiveOrder(Order order) {
        log.info("Order: {}", order);
        return order;
    }

}
