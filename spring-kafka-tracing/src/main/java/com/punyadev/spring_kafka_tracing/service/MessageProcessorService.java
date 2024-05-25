package com.punyadev.spring_kafka_tracing.service;

import com.punyadev.spring_kafka_tracing.dto.Message;

public interface MessageProcessorService {
    void processMessage(Message message);
}
