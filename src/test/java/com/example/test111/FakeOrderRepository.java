package com.example.test111;

import com.example.test111.order.IOrderRepository;
import com.example.test111.order.Order;
import com.example.test111.order.OrderId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeOrderRepository implements IOrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Optional<Order> getOrder(OrderId orderId) {
        return orders.stream()
                .filter(order -> order.equalsOrderId(orderId))
                .findFirst();
    }

    @Override
    public void save(Order order) {
        orders.add(order);
    }
}
