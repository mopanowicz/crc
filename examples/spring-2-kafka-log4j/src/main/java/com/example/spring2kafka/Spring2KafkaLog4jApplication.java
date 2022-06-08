package com.example.spring2kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring2KafkaLog4jApplication {

	public static void main(String[] args) {
	  System.out.println("Starting "+ System.currentTimeMillis());
		SpringApplication.run(Spring2KafkaLog4jApplication.class, args);
	}

}
