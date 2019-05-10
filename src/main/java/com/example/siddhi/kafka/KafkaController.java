package com.example.siddhi.kafka;

import com.example.siddhi.siddhi.ObjectDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final Producer producer;
    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    ObjectDispatcher dispatcher;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") long input) {
        this.producer.sendMessage(input);
        try {
            dispatcher.getInputHandler().send(new Object[]{input});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
