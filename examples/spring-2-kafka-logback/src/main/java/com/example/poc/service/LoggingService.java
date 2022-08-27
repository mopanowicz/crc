package com.example.poc.service;

import com.example.poc.logevent.LogEvent;
import com.example.poc.logevent.LogEventFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "logging.kafka")
@RequiredArgsConstructor
@Getter
@Setter
public class LoggingService {

    String topic;
    Map<String, String> properties;
    boolean serviceEnabled;
    boolean serviceBlocking;

    KafkaTemplate<Void, LogEvent> kafkaTemplate;

    private final LogEventFactory logEventFactory;

    @PostConstruct
    void init() {
        if (serviceEnabled) {
            Map<String, Object> config = properties.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            config.put("key.serializer", "org.apache.kafka.common.serialization.VoidSerializer");
            config.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");
            this.kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(config));
        }
        System.out.println("topic="+ topic);
        properties.forEach((k, v) -> System.out.println(k +"="+ v));
        System.out.println("serviceEnabled="+ serviceEnabled);
        System.out.println("serviceBlocking="+ serviceBlocking);
    }

    @SneakyThrows
    public void log(String loggerName, String level, String message) {
        LogEvent event = logEventFactory.logEvent(loggerName, level, message);
        event.setTopic(topic);
        ListenableFuture<SendResult<Void, LogEvent>> future = kafkaTemplate.send(topic, event);
        if (serviceBlocking) {
            future.get();
        }
    }
}
