package com.lagou.edu.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class MessageConsumerService {

    @StreamListener(Sink.INPUT)//监听
    public void recevieMessages(Message<String> message) {//import org.springframework.messaging.Message;
        System.out.println("=========接收到的消息：" + message);
    }
}
