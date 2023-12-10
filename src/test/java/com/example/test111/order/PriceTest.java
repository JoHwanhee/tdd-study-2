package com.example.test111.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void of() {
        assertThrows(IllegalArgumentException.class, () -> {
            Price.of(-1);
        });
    }
}