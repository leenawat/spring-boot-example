package com.punyadev.springkafkaauthdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {

    @Autowired
    private KafkaProducer producer;

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        producer.sendMessage("Hello, Kafka!");
    }
}