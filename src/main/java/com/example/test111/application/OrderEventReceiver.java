package com.example.test111.application;

import com.example.test111.order.OrderEvent;
import com.example.test111.payment.PaymentEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderEventReceiver {
    public void receive(OrderEvent event) {
        System.out.println("Received order event: " + event);
    }
}
