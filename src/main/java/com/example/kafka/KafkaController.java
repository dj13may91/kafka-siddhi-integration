package com.example.kafka;

import com.example.siddhi.ObjectDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;

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
    @Consumes("application/json")
    public void sendMessageToKafkaTopic(@RequestBody Person input) {
        this.producer.sendMessage(input);
        try {
            dispatcher.getInputHandler().send(new Person[]{input});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
