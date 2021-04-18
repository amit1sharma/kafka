package com.example.producer.controller;

import com.example.producer.component.TPKafkaProducer;
import com.example.producer.dto.MessageDto;
import com.example.producer.dto.RequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;

/**
 * @author yamraaj
 */
@RestController
public class SampleApiController {

    @Autowired
    private TPKafkaProducer producer;

    @PostMapping(value = "/message",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendMessageToQueue(@RequestBody RequestObject requestObject) throws Exception {

        String id = new Random().nextInt()+"";
        MessageDto dto = new MessageDto();
        dto.setAppName("amt");
        dto.setContextId(id);
        dto.setLogLevel(LogLevel.DEBUG.name());
        dto.setTime(System.currentTimeMillis());
        dto.setMessage(requestObject.getMessage());
        producer.pushMessage("amtopic", id,dto);
        return "message sent to topic";
    }

    @GetMapping(value = "/")
    public String serverUp() {
        return "server is running fine";
    }
}
