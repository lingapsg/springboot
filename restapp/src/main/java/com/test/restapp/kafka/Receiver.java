package com.test.restapp.kafka;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

    /*private Logger logger = LoggerFactory.getLogger(getClass());

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${topic.boot}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        logger.info("Received data {}",consumerRecord.toString());
        latch.countDown();
    }*/
}
