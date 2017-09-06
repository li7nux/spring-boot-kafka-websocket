package com.craftcoder.chcp;

import com.craftcoder.chcp.util.ChcpConstants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerTest {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);

    @Test
    public void producer() throws Exception {

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093");
        properties.put("key.serializer", StringSerializer.class);
        properties.put("value.serializer", StringSerializer.class);

        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(ChcpConstants.KAFKA_LISTENER_TOPIC.getTopic(),
                0, "message", "fuck me");

        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);

        RecordMetadata recordMetadata = future.get();


        logger.info("recordMetadata: {}", recordMetadata.toString());

    }

}
