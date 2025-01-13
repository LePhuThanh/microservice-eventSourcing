package com.phelim.notificationservice.event;

import org.apache.kafka.common.errors.RetriableException;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class EventConsumer {

    @RetryableTopic(
            attempts = "4", // 3 topic retry + 1 topic DLQ
            backoff = @Backoff(delay = 1000, multiplier = 2), // delay 1s
            autoCreateTopics = "true",
            dltStrategy = DltStrategy.FAIL_ON_ERROR,
            include = {
                    RetriableException.class, RuntimeException.class
            }
    )
    @KafkaListener(topics = "test", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        //log.info("Received Message: " + message);
        System.out.println("Received Message: " + message);
        //processing msg
        throw new RuntimeException("Error test");
    }

    //Monitoring DLQ
    @DltHandler
    void processDltMessage(@Payload String message){
//        log.info("DLT receive message: " + message);
        System.out.println("DLT receive message: " + message);
        //Handle conut retry ...
    }
}
