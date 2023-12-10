package com.example.test111.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OrderIdTest {

    @Test
    @DisplayName("should return value")
    void name() {
        OrderId orderId = OrderId.of("1");

        assertEquals("1", orderId.getValue());
    }

    @ParameterizedTest
    @MethodSource("sources")
    @DisplayName("null, empty, blank string should throw exception")
    void name2(String value) {
        assertThrows(IllegalArgumentException.class, () -> {
            OrderId.of(value);
        });
    }

    static Stream<String> sources() {
        return Stream.of(null, "", " ", "-1", "sdkfjk");
    }
}