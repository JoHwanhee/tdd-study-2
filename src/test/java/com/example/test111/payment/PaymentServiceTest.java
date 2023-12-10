package com.example.test111.payment;

import com.example.test111.order.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PaymentServiceTest {

    ApplicationEventPublisher publisherMock;
    IPaymentService sut;

    @BeforeEach
    void setUp() {
        publisherMock = mock(ApplicationEventPublisher.class);
        sut = new PaymentService(publisherMock);
    }

    @Test
    @DisplayName("amount가 null이면 IllegalArgumentException을 던진다")
    void pay() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> sut.pay(null));
    }

    @Test
    @DisplayName("amount가 null이 아니면, 이벤트를 발생시킨다")
    void pay2() {
        sut.pay(Price.of(1000));

        verify(publisherMock).publishEvent(new PaymentEvent(sut, Price.of(1000)));
    }
}