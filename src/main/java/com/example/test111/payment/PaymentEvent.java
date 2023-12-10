package com.example.test111.payment;

import com.example.test111.order.Price;
import org.springframework.context.ApplicationEvent;

public record PaymentEvent(
        Object source,
        Price amount
) {
    public PaymentEvent {
        if (source == null) throw new IllegalArgumentException("source is null");
        if (amount == null) throw new IllegalArgumentException("amount is null");
    }

    @Override
    public String toString() {
        return "PaymentEvent{" +
                "source=" + source +
                ", amount=" + amount +
                '}';
    }
}
