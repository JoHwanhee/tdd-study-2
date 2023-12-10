package com.example.test111.order;

import lombok.Value;

@Value(staticConstructor = "of")
public class Price {
    double value;

    private Price(double value) {
        this.value = validate(value);
    }

    private static double validate(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        return value;
    }
}
