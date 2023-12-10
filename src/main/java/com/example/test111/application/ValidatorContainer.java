package com.example.test111.application;

import com.example.test111.order.Order;

import java.util.List;

public class ValidatorContainer implements Validator {
    private final List<Validator> validators;

    public ValidatorContainer(List<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(Order order, OrderCommand orderCommand) {
        for (Validator validator : validators) {
            if (!validator.isValid(order, orderCommand)) {
                return false;
            }
        }

        return true;
    }
}

