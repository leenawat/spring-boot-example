package com.asb.example;

import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MyErrorHandler implements ConsumerAwareListenerErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(MyErrorHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        System.out.println("================================================");
        log.error("Error!!!");
        return null;
    }

}
