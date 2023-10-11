package com.example.kafka.poc.service.impl;

import com.example.kafka.poc.model.ExampleMessage;
import com.example.kafka.poc.service.KafkaPoCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaPoCServiceImpl implements KafkaPoCService {

    private static final String VALID_TOPIC_NAME = "valid_topic";
    private static final String BROKEN_TOPIC_NAME = "broken_topic";


    private final KafkaTemplate<String, ExampleMessage> kafkaTemplate;


    @Override
    public void sendMessageToValidTopic(String msg) {

        var exampleMessage = new ExampleMessage();

        exampleMessage.setTransactionId(msg);

        var future = kafkaTemplate.send(VALID_TOPIC_NAME, exampleMessage);
        wait(future, exampleMessage);
    }


    @Override
    public void sendMessageToBrokenTopic(String msg) {

        var exampleMessage = new ExampleMessage();

        exampleMessage.setTransactionId(msg);

        var future = kafkaTemplate.send(VALID_TOPIC_NAME, exampleMessage);
        wait(future, exampleMessage);
    }

    private static void wait(ListenableFuture<SendResult<String, ExampleMessage>> future, SpecificRecord msg) {

        System.out.println("done!!!!");
        //TODO safe-keeping-report-processor
    }


    @KafkaListener(topics = VALID_TOPIC_NAME)
    public void listenValidTopic(ExampleMessage message) {
        log.info("Received Message: " + message.getTransactionId());
    }


    @KafkaListener(topics = BROKEN_TOPIC_NAME)
    public void listenBrokenTopic(ExampleMessage message) {
        log.info("Received Message: " + message.getTransactionId());
    }

}
