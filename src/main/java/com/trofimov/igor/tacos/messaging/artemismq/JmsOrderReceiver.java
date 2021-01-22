/*
package com.trofimov.igor.tacos.messaging.artemismq;

import com.trofimov.igor.tacos.domain.Order;
import com.trofimov.igor.tacos.messaging.OrderReceiver;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@AllArgsConstructor
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jms;
    private MessageConverter converter;

*/
/*    @Override
    public Order receiveOrder() throws JMSException {
        Message message = jms.receive("tacocloud.order.queue");
        return (Order) converter.fromMessage(message);
    }*//*


    @Override
    public Order receiveOrder() throws JMSException {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }

}
*/
