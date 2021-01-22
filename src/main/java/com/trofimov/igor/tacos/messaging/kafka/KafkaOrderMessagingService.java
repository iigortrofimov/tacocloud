package com.trofimov.igor.tacos.messaging.kafka;

import com.trofimov.igor.tacos.domain.Order;
import com.trofimov.igor.tacos.messaging.OrderMessagingService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaOrderMessagingService implements OrderMessagingService {

    private KafkaTemplate<String, Order> kafka;

    @Override
    public void sendOrder(Order order) {
        kafka.send("tacocloud.orders.topic", order);
        kafka.sendDefault(order);
    }

}
