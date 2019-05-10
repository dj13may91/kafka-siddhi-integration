package com.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private static final String TOPIC = "consumer_topic";

    @Autowired
    private KafkaTemplate<String, Person> kafkaTemplate;

    public void sendToConsumer(Person message) {
        System.out.print("Sending message to consumer topic: ");
        System.out.println(message);
        this.kafkaTemplate.send(TOPIC, message);
        System.out.println("message sent to consumer topic successfully");
    }

//    @KafkaListener(topics = "consumer_topic")
//    public void consume(Person message) {
//        logger.info(String.format("$$ -> Consumed Message -> %s", message));
//    }
}
