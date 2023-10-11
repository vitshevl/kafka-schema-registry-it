package com.example.kafka.poc.service;

public interface KafkaPoCService {

    void sendMessageToValidTopic(String msg);
    void sendMessageToBrokenTopic(String msg);

}
