package com.example.test111;

import com.example.test111.order.Order;
import com.example.test111.order.OrderId;
import com.example.test111.order.Price;

public class ANY {

    public static OrderId ORDER_ID = OrderId.of("1");
    public static Price PRICE = Price.of(100);

    public static Order ORDER =  new Order(ORDER_ID, PRICE);
}
