package com.example.test111.application;

import com.example.test111.order.IExternalOrderService;
import com.example.test111.order.Order;
import com.example.test111.order.OrderId;
import com.example.test111.order.Price;
import com.example.test111.payment.IPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.test111.ANY.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


class OrderAndPaymentsServiceTest {
    IPaymentService paymentServiceMock;

    IExternalOrderService orderServiceMock;

    Validator validatorSpy;

    OrderAndPaymentsService sut;


    @BeforeEach
    void setup() {
        orderServiceMock = mock(IExternalOrderService.class);
        paymentServiceMock = mock(IPaymentService.class);
        var validator = new ValidatorContainer(List.of(new OrderPriceValidator()));
        validatorSpy = spy(validator);

        sut = new OrderAndPaymentsService(orderServiceMock, paymentServiceMock, validatorSpy);
    }

    @Test
    @DisplayName("주문 금액과, 상품 금액이 같으면 결제가 성공한다.")
    void test1() {
        var price = Price.of(100);
        var order = new Order(ORDER_ID, price);

        given(orderServiceMock.order(ORDER_ID)).willReturn(order);
        var command = OrderCommand.of(ORDER_ID, price);

        sut.processOrder(command);

        then(orderServiceMock).should(times(1)).order(OrderId.of("1"));
        then(validatorSpy).should(times(1)).isValid(order, command);
        then(paymentServiceMock).should(times(1)).pay(price);
    }

    @Test
    @DisplayName("주문 금액과, 상품 금액이 다르면 결제가 실패한다.")
    void test2() {
        // 상품은 100원짜리이고
        given(orderServiceMock.order(any())).willReturn(
                orderByPrice(100));

        // 주문 금액은 1000원이면,
        var command = OrderCommand.of(ORDER_ID, Price.of(1000));

        // 결제가 실패한다.
        assertThatThrownBy(() -> sut.processOrder(command))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Order is not valid");
    }

    private static Order orderByPrice(int price) {
        return new Order(ORDER_ID, Price.of(price));
    }
}