package com.example.homeproducer;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
public class MessageSenderTest {

    @SpyBean
    private MessageSender messageSender;

    @Test
    public void testScheduler() {
        Awaitility.await().atMost(15, TimeUnit.SECONDS).untilAsserted(() ->
                verify(messageSender, atLeast(1)).sendHome()
        );
    }
}
