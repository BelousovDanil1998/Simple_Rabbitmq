package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {
    private final HomeRepository homeRepository;

    @Autowired
    public MessageReceiver(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @RabbitListener(queues = "homeQueue")
    public void receiveHome(Home home) {
        System.out.println(" начал работу метода получения дома");
        HomeEntity homeEntity = new HomeEntity(home.getFloor());
        homeRepository.save(homeEntity);
        System.out.println("получил дом");
    }

}
