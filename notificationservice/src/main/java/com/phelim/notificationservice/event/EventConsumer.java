package com.phelim.notificationservice.event;

import com.phelim.commonservice.services.EmailService;
import org.apache.kafka.common.errors.RetriableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Slf4j
@Component
public class EventConsumer {
    @Autowired
    private EmailService emailService;

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

    @KafkaListener(topics = "testEmail", containerFactory = "kafkaListenerContainerFactory")
    public void listenEmail(String message) {
        //log.info("Received Message: " + message);
        System.out.println("Received Email Message: " + message);

        String temple = "<div>\n" +
                "    <h1>Welcome, %s!</h1>\n" +
                "    <p>Thank you for joining us. We're excited to have you on board.</p>\n" +
                "    <p>Your username is: <strong>%s</strong></p>\n" +
                "</div>";

        String filledTemplate = String.format(temple, "Phelim", message);

        emailService.sendEmail(message, "Thanks for support me", filledTemplate, true, null);
    }

    @KafkaListener(topics = "emailTemplate", containerFactory = "kafkaListenerContainerFactory")
    public void listenEmailTemplate(String message) {
        //log.info("Received Message: " + message);
        System.out.println("Received Email Template Message: " + message);

        //Add variable to placeholders whose value can change
        Map<String,Object> placeholders = new HashMap<>();
        placeholders.put("name", "Phelim's friend");

        emailService.sendEmailWithTemplate(message, "Welcome to Christmas", "emailTemplate.ftl", placeholders, null );
    }

}
