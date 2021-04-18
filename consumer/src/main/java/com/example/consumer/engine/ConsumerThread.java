package com.example.consumer.engine;

import com.example.consumer.consumer.AbstractConsumer;
import com.example.consumer.factory.ConsumerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author yamraaj
 */
@Component
@Scope("prototype")
public class ConsumerThread implements Runnable{


    private KafkaConsumer< String , String> kafkaConsumer;
    private  String topic;
    private int streamNumber;
    private AbstractConsumer tpConsumer;

    @Autowired
    private ConsumerFactory consumerFactory;

    public ConsumerThread(){

    }

    public ConsumerThread( KafkaConsumer< String , String> kafkaConsumer , String topic , int streamNumber){
        this.kafkaConsumer = kafkaConsumer;
        this.topic = topic;
        this.streamNumber = streamNumber;
        this.tpConsumer = consumerFactory.getConsumerImplementation(topic);
    }

    public KafkaConsumer<String, String> getKafkaConsumer() {
        return kafkaConsumer;
    }

    public void setKafkaConsumer(KafkaConsumer<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getStreamNumber() {
        return streamNumber;
    }

    public void setStreamNumber(int streamNumber) {
        this.streamNumber = streamNumber;
    }

    public AbstractConsumer getTpConsumer() {
        return tpConsumer;
    }

    public void setTpConsumer(AbstractConsumer tpConsumer) {
        this.tpConsumer = tpConsumer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(0);
                int i = 0;
                for (ConsumerRecord<String, String> record : records) {
                    String message = record.value();
                    try {
                        tpConsumer.consumeMessage(message);
                        i++;
                        if (i == 100) {
                            i = 0;
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
