package com.trofimov.igor.tacos.messaging.kafka;

import com.trofimov.igor.tacos.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaOrderListener {

    @KafkaListener(topics = "tacocloud.orders.topic")
    public Order receiveOrder(Order order, ConsumerRecord<String, Order> orderRecord) { // + ConsumerRecords / Message
        log.info("Order: {} + additional data: 1. partition -  {}, timestamp - {}", order, orderRecord.partition(),
                orderRecord.timestamp());
        return order;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(Order order, Message<Order> message) {
        MessageHeaders headers = message.getHeaders();
        log.info("Received from partition {} with timestamp {}",
                headers.get(KafkaHeaders.RECEIVED_PARTITION_ID),
                headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
    }
}
