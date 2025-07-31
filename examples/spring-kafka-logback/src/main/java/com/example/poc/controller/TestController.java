package com.example.poc.controller;

import com.example.poc.logevent.LogEvent;
import com.example.poc.logevent.LogEventFactory;
import com.example.poc.service.LoggingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @Value("${test-controller.default-sleep-ms:0}")
    long defaultSleepMillis;

    @Value("${test-controller.system-out:false}")
    boolean systemOut;

    private final LoggingService loggingService;
    private final LogEventFactory logEventFactory;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    String test(
            @RequestParam(name = "sleepMillis", defaultValue = "0", required = false) long sleepMillis,
            @RequestParam(name = "messageLength", defaultValue = "32", required = false) int messageLength,
            @RequestParam(name = "messageCount", defaultValue = "1", required = false) int messageCount,
            @RequestParam(name = "id", defaultValue = "0", required = false) long id
    ) throws InterruptedException {

        long start = System.currentTimeMillis();

        log("start=" + start);

        long sleep = sleepMillis > 0 ? sleepMillis : defaultSleepMillis;

        String message = RandomStringUtils.randomAlphabetic(messageLength);
        for (int i = 0; i < messageCount; i++) {
            log(MessageFormat.format("test id={0} sleep={1}ms iteration={2} message=\"{3}\"", id, sleep, i, message));
            if (sleep > 0) {
                Thread.sleep(sleep);
            }
        }

        log("test done in " + (System.currentTimeMillis() - start) + "ms");

        return "{\"timestamp\": " + System.currentTimeMillis() + "}";
    }

    Map<String, Integer> counters = new HashMap<>();

    @AllArgsConstructor
    @Getter
    static class Message {
        String scope;
        Integer iteration;
        Integer counter;
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Message count(
            @RequestParam(name = "scope", defaultValue = "none", required = false) String scope,
            @RequestParam(name = "iteration", defaultValue = "0", required = false) int iteration,
            @RequestParam(name = "reset", defaultValue = "false", required = false) boolean reset
    ) throws JsonProcessingException {

        long start = System.currentTimeMillis();

        Integer counter = counters.get(scope);
        if (counter == null || reset) {
            counter = 0;
        }
        if (!reset) {
            counter++;
        }
        counters.put(scope, counter);

        Message message = new Message(scope, iteration, counter);

        log(objectMapper.writeValueAsString(message));

        return message;
    }

    void log(String msg) {
        if (loggingService.isServiceEnabled()) {
            loggingService.log(getClass().getName(), "INFO", msg);
        } else {
            log.info(msg);
        }
        LogEvent logEvent = logEventFactory.logEvent(getClass().getName(), "INFO", msg);
        if (systemOut) {
            System.out.println(logEvent.toString());
        }
    }
}
