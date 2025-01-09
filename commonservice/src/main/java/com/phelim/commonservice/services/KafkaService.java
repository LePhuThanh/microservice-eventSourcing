package com.phelim.commonservice.services;

//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Slf4j
@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
        //log.debug("Message send to topic");
        System.out.println("Message send to topic" + topic);
    }
}
