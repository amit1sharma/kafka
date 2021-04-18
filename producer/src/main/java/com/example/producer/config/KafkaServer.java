package com.example.producer.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Properties;



@Component
public class KafkaServer {

//    Logger logger = Logger.getLogger(KafkaServer.class);

    private Properties properties;

    private KafkaProducer kafkaProducer;

    private static String env = System.getProperty("spring.profiles.active");

    @PostConstruct
    public void initKafkaProducer(){
        try{
            InputStream props = this.getClass().getClassLoader().getResourceAsStream("producer.properties");
            Properties properties = new Properties();
            properties.load(props);
           /* InputStream props = Resources.getResource(env + "/producer.properties").openStream();
            properties = new Properties();
            properties.load(props);*/
            kafkaProducer = new KafkaProducer(properties);

        }
        catch ( Exception ex){
//            logger.error(" Can not read properties file");
            System.out.println("Can not read properties file");
        }


    }

    public KafkaProducer getKafkaProducer(){
        return kafkaProducer;
    }
}
