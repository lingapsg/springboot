package com.test.restapp.kafka;

import org.springframework.stereotype.Component;

@Component
public class Sender {

    /*private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Sender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topicName, String data) {
        logger.info("Sending Message {} to topic {}",data, topicName);
        kafkaTemplate.send(topicName, data);
    }*/
}
