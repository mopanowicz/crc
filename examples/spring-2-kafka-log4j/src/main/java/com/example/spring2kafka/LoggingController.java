package com.example.spring2kafka;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoggingController {

	@GetMapping("/log")
	String log() {
		String status = "{\"status\":\"ok\"}";
		if (log.isDebugEnabled()) {
			log.debug(status);
		}
		if (log.isInfoEnabled()) {
			log.info(status);
		}
		return status;
	}
}
