package com.punyadev.spring_kafka_tracing.consumer;

import com.punyadev.spring_kafka_tracing.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageConsumer2  {

    Logger log = LoggerFactory.getLogger(MessageConsumer2.class);
    @KafkaListener(
            topics = "topic-message",
//            groupId = "message-consumer-group",
            containerFactory = "messageContainerFactory")
    public void consumeMessage(@Payload Message message) {
        log.info("Consumed kafka message {}", message);
    }


}
