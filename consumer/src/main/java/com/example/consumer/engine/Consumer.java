package com.example.consumer.engine;

import com.example.consumer.factory.ConsumerFactory;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yamraaj
 */
@Component
public class Consumer {

    @Value("${kafka.consumer.streams.count}")
    private int NO_OF_CONSUMER_STREAMS;

    @Autowired
    private Environment environment;

    @Value("#{'${kafka.consumer.topic.list}'.split(',')}")
    private List<String> TOPICS_LIST;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ConsumerFactory consumerFactory;

    public void initConsumer(){
        try {
            InputStream props = this.getClass().getClassLoader().getResourceAsStream("consumer.properties");
            Properties properties = new Properties();
            properties.load(props);

            ExecutorService executor = Executors.newFixedThreadPool(TOPICS_LIST.size());
            for(String topic : TOPICS_LIST) {
                topic = topic.trim();
                int noOfStreams = NO_OF_CONSUMER_STREAMS;
                String count = environment.getProperty(topic+".thread.count");
                if(count!=null) {
                    noOfStreams = Integer.parseInt(count);
                }
                System.out.println(  " topic name is " + topic + " || no of streams is "+noOfStreams );
                for(int i=0; i < noOfStreams; i++) {
                    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
                    kafkaConsumer.subscribe(Arrays.asList(topic));
                    ConsumerThread consumerThread = applicationContext.getBean(ConsumerThread.class);
                    consumerThread.setKafkaConsumer(kafkaConsumer);
                    consumerThread.setTopic(topic);
                    consumerThread.setStreamNumber(i);
                    consumerThread.setTpConsumer(consumerFactory.getConsumerImplementation(topic));
                    executor.submit(consumerThread);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
