package com.example.consumer.factory;

import com.example.consumer.consumer.AbstractConsumer;
import com.example.consumer.consumer.impl.SampleConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yamraaj
 */
@Component
public class ConsumerFactory {
    

    @Autowired
    private SampleConsumer sampleConsumer;
    
    public AbstractConsumer getConsumerImplementation(String topic) {
        return sampleConsumer;
    }
}
