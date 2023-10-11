package com.example.kafka.poc;


import com.example.kafka.poc.model.ExampleMessage;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@EmbeddedKafka()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseItTest {

    @Autowired
    private KafkaProperties kafkaProperties;
    @Autowired
    private EmbeddedKafkaBroker kafkaEmbedded;
    protected Producer<String, ExampleMessage> exampleMessageProducer;
    protected Consumer<String, ExampleMessage> exampleMessageConsumer;



    @BeforeAll
    public void setUp() {
        Map<String, Object> senderProps = kafkaProperties.buildProducerProperties();

        exampleMessageProducer = new KafkaProducer<>(senderProps);

        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("in-test-consumer", "false", kafkaEmbedded));

        exampleMessageConsumer = new DefaultKafkaConsumerFactory<String, ExampleMessage>(configs).createConsumer("in-test-consumer");

        kafkaProperties.buildConsumerProperties();

        exampleMessageConsumer.subscribe(Lists.newArrayList("valid_topic"));
    }

    @AfterAll
    public void reset() {
        //consumers needs to be closed because new one are created before every test
        exampleMessageConsumer.close();
    }
}
