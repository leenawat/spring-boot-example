package com.punyadev.spring_kafka_tracing.config;


import com.punyadev.spring_kafka_tracing.dto.Message;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public KafkaTemplate<String, Message> messageKafkaTemplate(
            ProducerFactory<String, Message> messageProducerFactory) {

        KafkaTemplate<String, Message> template = new KafkaTemplate<>(messageProducerFactory);
        template.setObservationEnabled(true);

        return template;
    }
}