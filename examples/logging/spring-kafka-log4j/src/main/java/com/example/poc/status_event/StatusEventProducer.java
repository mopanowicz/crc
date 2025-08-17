package com.example.poc.status_event;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@Slf4j
public class StatusEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${status-event-producer.topic}")
    String topic;
    @Value("${status-event-producer.key}")
    String key;
    @Value("${status-event-producer.blocking:false}")
    boolean blocking;

    public StatusEventProducer(@Qualifier("statusEventKafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Object event) {
        try {
            log.debug("send topic={} key={} event={}", topic, key, event);
            ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, key, event);
            Future<SendResult<String, Object>> future = kafkaTemplate.send(producerRecord);
            if (blocking) {
                future.get();
            }
        } catch (Exception e) {
            log.error("Exception while sending event={}", event, e);
        }
    }
}
