package com.example.rocketmqDemo.controller;

import com.example.rocketmqDemo.rocketmq.RocketMQConsumer;
import com.example.rocketmqDemo.rocketmq.RocketMQProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    RocketMQProvider rocketMQProvider;


    @RequestMapping("testMQ")
    public String testMq() {
        rocketMQProvider.defaultMQProducer();
        return null;
    }
}
