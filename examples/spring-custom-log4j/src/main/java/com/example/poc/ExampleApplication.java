package com.example.poc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

	@PostConstruct
	void init() {
		log.trace("init trace");
		log.debug("init debug");
		log.info("init info");
		log.warn("init warn");
		log.error("init error");
	}
}
