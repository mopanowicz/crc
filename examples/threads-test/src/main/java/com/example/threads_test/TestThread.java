package com.example.threads_test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThread extends Thread {
    @Override
    public void run() {
        log.info("started");
        while (true) {
            try {
                long time = 1000;
                log.debug("sleeping {}ms", time);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                log.error("interrupted");
            }
        }
    }
}
