package com.trofimov.igor.tacos.messaging;

import com.trofimov.igor.tacos.domain.Order;

import javax.jms.JMSException;

public interface OrderReceiver {

    Order receiveOrder() throws JMSException;
}
