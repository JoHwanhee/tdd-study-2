package com.example.test111.application;

import com.example.test111.order.Order;

public interface Validator {
    boolean isValid(Order order, OrderCommand orderCommand);
}