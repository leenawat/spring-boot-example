package com.asb.example;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class KafkaConsumer {


    @KafkaListener(topics ="${kafka.topic}", groupId = "${kafka.group.id}")
    public void processMessage(String msg) {
        System.out.println("Processing message: " + msg);
        log.info("Processing message : {}", msg);
    }


}

