package com.example.threads_test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThread extends Thread {
    @Override
    public void run() {
        log.info("started");
    }
}
