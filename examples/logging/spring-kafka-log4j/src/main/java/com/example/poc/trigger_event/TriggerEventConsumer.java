package com.example.poc.trigger_event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;
import com.example.poc.status_event.StatusEvent;
import com.example.poc.status_event.StatusEventProducer;

@Component
@Slf4j
class TriggerEventConsumer {

    @Autowired
    StatusEventProducer statusEventProducer;

    int count;
    long startTimestamp;

    @Value("${trigger-event-consumer.status-interval}")
    int statusInterval;
    @Value("${trigger-event-consumer.log-repeat}")
    int logRepeat;
    @Value("${trigger-event-consumer.log-cargo-length}")
    int logCargoLength;

    @KafkaListener(topics = "${trigger-event-consumer.topic}", containerFactory = "triggerEventListenerContainerFactory")
    void processTriggerEvent(TriggerEvent event) {
        if (count == 0) {
            startTimestamp = System.currentTimeMillis();
        }
        count++;
        for (int i = 0; i < logRepeat; i++) {
            log.info("processTriggerEvent event={} count={} repeat={} cargo={}", event, count, i, StringUtils.randomAlphanumeric(logCargoLength));
        }
        if (count % statusInterval == 0) {
            statusEventProducer.send(new StatusEvent(event.batch(), event.index(), event.timestamp(), count, (System.currentTimeMillis() - startTimestamp) / 1000));
        }
    }
}
