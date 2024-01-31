package com.punyadev.springkafkaconsumerdemo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    // simeple
//    @KafkaListener(topics = "test")
//    public void processMessage(String content){
//        System.out.println("Message received: " + content);
//    }

    // 2) Multiple consumer groups reading from the same topic
    @KafkaListener(topics = "test", groupId = "myGroup")
    public void processMessage11(String content) {
        System.out.println("Message received by consumer 1: " + content);
    }

    @KafkaListener(topics = "test", groupId = "anotherGroup")
    public void processMessage2(String content) {
        System.out.println("Message received by consumer 2: " + content);
    }
}