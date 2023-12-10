package com.example.test111.order;

import java.util.Optional;

public interface IOrderRepository {
    Optional<Order> getOrder(OrderId orderId);
    void save(Order order);
}