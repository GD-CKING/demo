package com.comsumer.rabbitmq.listener;

import com.comsumer.rabbitmq.consumer.HelloBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(HelloBinding.class)
public class HelloListener {

    @StreamListener(target = HelloBinding.GERRTING)
    public void processHelloChannelGreeting(String msg) {
        System.out.println(msg);
    }
}
