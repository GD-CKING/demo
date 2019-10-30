package com.comsumer.rabbitmq.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface HelloBinding {

    String GERRTING = "helloChannel";

    @Input(GERRTING)
    SubscribableChannel greeting();
}
