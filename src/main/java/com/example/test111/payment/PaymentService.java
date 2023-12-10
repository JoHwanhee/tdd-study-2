package com.example.test111.payment;

import com.example.test111.order.Price;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Objects;

@AllArgsConstructor
public class PaymentService implements IPaymentService{

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void pay(Price amount) {
        if ( Objects.isNull(amount) ) throw new IllegalArgumentException("amount is null");

        applicationEventPublisher.publishEvent(new PaymentEvent(this, amount));

        System.out.println("PaymentService.pay");
    }
}