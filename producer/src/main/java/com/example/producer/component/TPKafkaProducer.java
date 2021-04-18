package com.example.producer.component;

import com.example.producer.config.KafkaServer;
import com.example.producer.dto.MessageDto;
import com.example.producer.utils.JSONConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class TPKafkaProducer {


    @Autowired
    private KafkaServer kafkaServer;

    public void pushMessage(String topic, String key, MessageDto message) throws Exception {
        System.out.println(" Pushing data " + message + "on topic " + topic);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, key,
                JSONConverter.convertPojoToJSONObject(message).toString());
        System.out.println(" Producer Record created : " + producerRecord.toString());
        kafkaServer.getKafkaProducer().send(producerRecord);
        System.out.println(" Data pushed successfully '" + message + "' on topic " + topic);

    }

}
