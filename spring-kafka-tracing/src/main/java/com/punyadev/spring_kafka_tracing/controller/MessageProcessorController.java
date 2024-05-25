package com.punyadev.spring_kafka_tracing.controller;

import com.punyadev.spring_kafka_tracing.dto.Message;
import com.punyadev.spring_kafka_tracing.service.MessageProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProcessorController {

    @Autowired
    private Environment environment;



    Logger log = LoggerFactory.getLogger(MessageProcessorController.class);

    private final MessageProcessorService messageProcessorService;

    public MessageProcessorController(MessageProcessorService messageProcessorService) {
        this.messageProcessorService = messageProcessorService;
    }

    @PostMapping("/process-message")
    public void processMessage(@RequestBody Message message) {
        String consolePatternKey = "logging.pattern.console";

        // Getting the console logging pattern
        String consolePattern = environment.getProperty(consolePatternKey);
        System.out.println("Console Logging Pattern: " + consolePattern);

        log.info("Received message for processing {}", message);
        messageProcessorService.processMessage(message);
    }
}
