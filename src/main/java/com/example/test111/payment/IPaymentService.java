package com.example.test111.payment;

import com.example.test111.order.Price;

public interface IPaymentService {
    void pay(Price amount);
}