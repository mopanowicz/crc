package com.example.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaLog4jApplication {

    public static void main(String[] args) {
        System.out.println("Starting " + System.currentTimeMillis());
        SpringApplication.run(SpringKafkaLog4jApplication.class, args);
    }

}
