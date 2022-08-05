package com.example.poc;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@Log4j2
public class ExampleApplication {

	final static Level AUDIT = Level.forName("AUDIT", 150);

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
		log.log(AUDIT, "init audit");
	}

	@PreDestroy
	void done() {
		log.trace("done trace");
		log.debug("done debug");
		log.info("done info");
		log.warn("done warn");
		log.error("done error");
		log.log(AUDIT, "done audit");
	}
}
