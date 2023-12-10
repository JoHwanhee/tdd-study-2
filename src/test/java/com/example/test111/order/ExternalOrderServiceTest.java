package com.example.test111.order;

import com.example.test111.FakeOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static com.example.test111.ANY.ORDER_ID;
import static com.example.test111.ANY.PRICE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class ExternalOrderServiceTest {

    ApplicationEventPublisher applicationEventPublisherMock;
    IOrderRepository orderRepositorySpy;
    IExternalOrderService sut;

    @BeforeEach
    void setUp() {
        applicationEventPublisherMock = mock(ApplicationEventPublisher.class);

        var orderRepository = new FakeOrderRepository();
        orderRepositorySpy = spy(orderRepository);

        sut = new ExternalOrderService(applicationEventPublisherMock, orderRepositorySpy);
    }

    @Test
    @DisplayName("주문이 있는 경우")
    void name() {
        orderRepositorySpy.save(new Order(ORDER_ID, PRICE));

        sut.order(ORDER_ID);

        then(orderRepositorySpy).should().getOrder(ORDER_ID);
        then(applicationEventPublisherMock).should().publishEvent(new OrderEvent(sut, ORDER_ID));
    }

    @Test
    @DisplayName("주문이 없는 경우")
    void name2() {
        assertThrows(IllegalStateException.class, () -> sut.order(ORDER_ID));

        then(applicationEventPublisherMock).should(times(0)).publishEvent(any());
    }
}