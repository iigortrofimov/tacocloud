package com.trofimov.igor.tacos.messaging;

import com.trofimov.igor.tacos.domain.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        System.out.println(order);
    }

}
