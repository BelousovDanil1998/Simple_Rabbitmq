package com.example.homeproducer;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    private int messageCounter = 0;


    @Scheduled(fixedDelay = 10000)
    public void sendHome() {
        Home home = new Home(messageCounter++);
        rabbitTemplate.convertAndSend("homeExchange", "homeRoutingKey", home);
        System.out.println("послал дом №" + messageCounter);
    }
}
