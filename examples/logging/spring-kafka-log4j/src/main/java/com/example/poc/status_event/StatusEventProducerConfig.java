package com.example.poc.status_event;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "status-event-producer")
public class StatusEventProducerConfig {

    @Setter
    Map<String, String> properties;

    @Bean("statusEventProducerFactory")
    ProducerFactory<String, Object> statusEventProducerFactory() {
        Map<String, Object> configs = properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())));
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean("statusEventKafkaTemplate")
    public KafkaTemplate<String, Object> statusEventKafkaTemplate(@Qualifier("statusEventProducerFactory") ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
