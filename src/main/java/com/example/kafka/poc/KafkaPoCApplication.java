package com.example.kafka.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@EnableKafka
@SpringBootApplication
public class KafkaPoCApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaPoCApplication.class, args);
	}

}
