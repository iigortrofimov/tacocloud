package com.trofimov.igor.tacos.messaging;

import com.trofimov.igor.tacos.domain.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);

}
