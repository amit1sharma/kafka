package com.example.consumer.consumer.impl;

import com.example.consumer.consumer.AbstractConsumer;
import com.example.consumer.dto.MessageDto;
import com.example.consumer.service.LogService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author yamraaj
 */
@Component
public class SampleConsumer implements AbstractConsumer {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private LogService logService;


    @Override
    public void consumeMessage(String message) {

        if(StringUtils.isEmpty(message)) {
        }
        else
        {
            try{
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                JsonNode msg = mapper.readTree(message);
                MessageDto dto = mapper.convertValue(msg, MessageDto.class);
                logService.saveLog(dto);
                System.out.println("saved successfully.");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }

    }
}
