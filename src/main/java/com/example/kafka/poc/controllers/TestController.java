package com.example.kafka.poc.controllers;


import com.example.kafka.poc.dto.MessageDto;
import com.example.kafka.poc.service.KafkaPoCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final KafkaPoCService kafkaPoCService;

    @PostMapping(value = "/valid-topic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendToValid(@RequestBody MessageDto request) {

       kafkaPoCService.sendMessageToValidTopic(request.getMessage());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/broken-topic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendToBroken(@RequestBody MessageDto request) {

        kafkaPoCService.sendMessageToBrokenTopic(request.getMessage());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
