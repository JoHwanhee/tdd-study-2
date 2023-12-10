package com.example.test111.order;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@AllArgsConstructor
public class ExternalOrderService implements IExternalOrderService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final IOrderRepository orderRepository;

    public Order order(OrderId orderId) {
        var order = orderRepository.getOrder(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found"));

        applicationEventPublisher.publishEvent(new OrderEvent(this, orderId));
        return order;
    }
}