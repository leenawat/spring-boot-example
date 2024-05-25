package com.punyadev.spring_kafka_tracing.consumer;

import com.punyadev.spring_kafka_tracing.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

//@Component
public class MessageConsumer extends AbstractConsumerSeekAware {

    Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "topic-message", groupId = "message-consumer-group", containerFactory = "messageContainerFactory")
    public void consumeMessage(@Payload Message message) {
        System.out.println("Consumed kafka message " + message);
        log.info("Consumed kafka message {}", message);
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekAware.ConsumerSeekCallback callback) {
        assignments.keySet().stream()
                .filter(partition -> "topic-message".equals(partition.topic()))
                .forEach(partition -> callback.seekToBeginning("topic-message", partition.partition()));

    }

}
