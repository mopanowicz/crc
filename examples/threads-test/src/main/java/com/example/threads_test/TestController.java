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

    Map<String, Thread> threads = new HashMap<>();

    @GetMapping(value = "new-thread", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> newThread(@RequestParam(defaultValue = "1") int numberOfThreads) {
        log.info("newThread numberOfThreads={}", numberOfThreads);
        Map<String, Object> states = new HashMap<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new TestThread();
            thread.start();
            states.put(thread.getName(), thread.getState());
            threads.put(thread.getName(), thread);
        }
        return states;
    }

    @GetMapping(value = "thread-states", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> threadStates() {
        Map<String, Object> states = new HashMap<>();
        for (var entry : threads.entrySet()) {
            states.put(entry.getKey(), entry.getValue().getState());
        }
        return states;
    }
}
