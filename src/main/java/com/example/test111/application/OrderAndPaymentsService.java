package com.example.test111.application;

import com.example.test111.order.IExternalOrderService;
import com.example.test111.order.Order;
import com.example.test111.order.OrderId;
import com.example.test111.order.Price;
import com.example.test111.payment.IPaymentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderAndPaymentsService {
    private final IExternalOrderService orderService;
    private final IPaymentService paymentService;
    private final Validator orderValidator;

    public void processOrder(OrderCommand orderCommand) {
        Order order = order(orderCommand);

        validateOrThrow(orderCommand, order);

        pay(orderCommand);
    }

    private Order order(OrderCommand orderCommand) {
        OrderId orderId = orderCommand.getOrderId();
        return orderService.order(orderId);
    }

    private void validateOrThrow(OrderCommand orderCommand, Order order) {
        if(!orderValidator.isValid(order, orderCommand)){
            throw new IllegalStateException("Order is not valid");
        }
    }

    private void pay(OrderCommand orderCommand) {
        Price priceForOrder = orderCommand.getTotal();
        paymentService.pay(priceForOrder);
    }
}