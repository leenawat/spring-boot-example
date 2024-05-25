package com.punyadev.spring_kafka_tracing.config;

import com.punyadev.spring_kafka_tracing.dto.Message;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.config.*;
import org.springframework.kafka.core.*;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> messageContainerFactory(
            ConsumerFactory<String, Message> messageConsumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, Message> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.getContainerProperties().setObservationEnabled(true);

        factory.setConsumerFactory(messageConsumerFactory);

        return factory;
    }

}
