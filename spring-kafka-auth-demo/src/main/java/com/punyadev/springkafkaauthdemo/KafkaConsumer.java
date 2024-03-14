package com.punyadev.springkafkaauthdemo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
