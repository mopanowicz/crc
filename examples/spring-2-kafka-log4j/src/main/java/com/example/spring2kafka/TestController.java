package com.example.spring2kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

  @Value("${test.controller.sleep.ms}")
  long sleepMillis;

  @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  String test() throws InterruptedException {
    String status = "{\"status\":\"ok\"}";
    if (log.isDebugEnabled()) {
      log.debug(status);
    }
    if (log.isInfoEnabled()) {
      log.info(status);
    }
    if (sleepMillis > 0) {
      Thread.sleep(sleepMillis);
    }
    return status;
  }
}
