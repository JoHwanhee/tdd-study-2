package com.example.test111.order;

public class Order {
    private OrderId orderId;
    private Price total;

    public Order(OrderId orderId, Price total) {
        this.orderId = orderId;
        this.total = total;
    }

    public Price getTotal() {
        return total;
    }

    public boolean equalsOrderId(OrderId orderId) {
        return this.orderId.equals(orderId);
    }
}

