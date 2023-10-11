package com.example.kafka.poc;

import com.example.kafka.poc.model.ExampleMessage;
import com.example.kafka.poc.service.KafkaPoCService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class KafkaPoCApplicationTests extends BaseItTest {

	@Autowired
	private KafkaPoCService kafkaPoCService;

	@Test
	public void should_send_event1() {
		kafkaPoCService.sendMessageToValidTopic("test");

		ConsumerRecord<String, ExampleMessage> singleRecord = KafkaTestUtils.getSingleRecord(exampleMessageConsumer, "valid_topic");
		assertThat(singleRecord).isNotNull();
	}

}
