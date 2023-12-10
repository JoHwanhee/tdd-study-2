package com.example.test111.order;

import com.example.test111.utils.StringId;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderId implements StringId {

    String value;

    private OrderId(String value) {
        this.value = validate(value);
    }

    @Override
    public String get() {
        return this.value;
    }

    private static String validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("OrderId cannot be null or empty");
        }

        try {
            var parsed = Long.parseLong(value);
            if (parsed < 0) {
                throw new IllegalArgumentException("OrderId cannot be negative");
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("OrderId must be a number");
        }

        return value;
    }
}
