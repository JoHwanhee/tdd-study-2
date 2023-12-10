package com.example.test111.application;

import com.example.test111.order.OrderId;
import com.example.test111.order.Price;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderCommand {
    OrderId orderId;
    Price total;
}