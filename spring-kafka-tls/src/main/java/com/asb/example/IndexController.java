package com.asb.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private final KafkaProducer kafkaProducer;

    public IndexController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping
    public void sendUser() {
        kafkaProducer.sendMessage("jack");
    }
}
