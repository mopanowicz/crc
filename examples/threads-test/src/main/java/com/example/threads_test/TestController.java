package com.example.threads_test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @GetMapping(value = "new-thread", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> newThread(@RequestParam(defaultValue = "1") int numberOfThreads) {
        log.info("newThread numberOfThreads={}", numberOfThreads);
        Map<String, Object> states = new HashMap<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread testThread = new TestThread();
            testThread.start();
            states.put(testThread.getName(), testThread.getState());
        }
        return states;
    }
}
