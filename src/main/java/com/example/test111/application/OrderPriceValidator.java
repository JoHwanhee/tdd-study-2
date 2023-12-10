package com.example.test111.application;

import com.example.test111.order.Order;

public class OrderPriceValidator implements Validator {
    @Override
    public boolean isValid(Order order, OrderCommand orderCommand) {
        return orderCommand.getTotal().equals(order.getTotal());
    }
}
