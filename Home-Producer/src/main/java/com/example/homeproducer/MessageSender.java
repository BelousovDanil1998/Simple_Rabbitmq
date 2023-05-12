package com.example.homeproducer;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostConstruct
    public void sendHome() {
        Home home = new Home(123);
        rabbitTemplate.convertAndSend("homeExchange", "home.create", home);
        System.out.println("послал дом");
    }
}
