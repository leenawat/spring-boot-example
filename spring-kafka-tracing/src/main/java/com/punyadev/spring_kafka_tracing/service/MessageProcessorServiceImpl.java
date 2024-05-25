package com.punyadev.spring_kafka_tracing.service;

import com.punyadev.spring_kafka_tracing.controller.MessageProcessorController;
import com.punyadev.spring_kafka_tracing.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessorServiceImpl implements MessageProcessorService {
    Logger log = LoggerFactory.getLogger(MessageProcessorServiceImpl.class);
    private final KafkaTemplate<String, Message> messageKafkaTemplate;

    public MessageProcessorServiceImpl(KafkaTemplate<String, Message> messageKafkaTemplate) {
        this.messageKafkaTemplate = messageKafkaTemplate;
    }

    @Override
    public void processMessage(Message message) {
        log.info("Processing Message {}", message);
        messageKafkaTemplate.send("topic-message", message);
    }
}