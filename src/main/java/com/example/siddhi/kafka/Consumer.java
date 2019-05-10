package com.example.siddhi.kafka;

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
    private KafkaTemplate<String, String> kafkaTemplate;

//    @KafkaListener(topics = "producer_topic", groupId = "group_id")
    public void sendToConsumer(long message) {
        this.kafkaTemplate.send(TOPIC, message + "");
    }

    @KafkaListener(topics = "consumer_topic")
    public void consume(long message) {
        logger.info(String.format("$$ -> Consumed Message -> %s", message));
    }
}
