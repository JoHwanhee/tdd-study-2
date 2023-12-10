package com.example.test111.application;

import com.example.test111.order.Price;
import com.example.test111.payment.PaymentEvent;
import com.example.test111.utils.ILogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PaymentEventReceiverTest {

    ILogger logger = mock(ILogger.class);

    PaymentEventReceiver sut;

    @BeforeEach
    void setUp() {
        sut = new PaymentEventReceiver(logger);
    }

    @Test
    void onApplicationEvent() {
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);


        sut.receive(new PaymentEvent(this, Price.of(1000)));


        verify(logger).log(logCaptor.capture());
        String loggedMessage = logCaptor.getValue();
        assertTrue(loggedMessage.contains("1000"));
    }
}