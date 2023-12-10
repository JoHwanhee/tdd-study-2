package com.example.test111.order;

public record OrderEvent (
        Object source,
        OrderId orderId
) {
    public OrderEvent {
        if (source == null) throw new IllegalArgumentException("source is null");
        if (orderId == null) throw new IllegalArgumentException("orderId is null");
    }


}
