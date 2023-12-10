package com.example.test111.application;

import com.example.test111.payment.PaymentEvent;
import com.example.test111.utils.ILogger;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentEventReceiver {

    private final ILogger logger;

    public void receive(PaymentEvent event) {
        logger.log("Received payment event: " + event);
    }
}
