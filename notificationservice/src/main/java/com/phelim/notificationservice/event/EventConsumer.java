package com.phelim.notificationservice.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class EventConsumer {

    @KafkaListener(topics = "test", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        //log.info("Received Message: " + message);
        System.out.println("Received Message: " + message);
    }
}
