package com.example.poc.controller;

import com.example.poc.config.LogConfig;
import com.example.poc.service.LoggingService;
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

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @Value("${test-controller.default-sleep-ms}")
    long defaultSleepMillis;

    private final LoggingService loggingService;

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

    void log(String msg) {
        if (loggingService.isServiceEnabled()) {
            loggingService.log(getClass().getName(), "INFO", msg);
        } else {
            log.info(LogConfig.AUDIT, msg);
        }
    }
}
