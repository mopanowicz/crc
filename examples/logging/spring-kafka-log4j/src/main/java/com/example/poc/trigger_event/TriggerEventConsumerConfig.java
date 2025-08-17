package com.example.poc.trigger_event;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "trigger-event-consumer")
class TriggerEventConsumerConfig {

    @Setter
    Map<String, String> properties;

    @Bean("triggerEventListenerContainerFactory")
    ConcurrentKafkaListenerContainerFactory<String, Object> triggerEventListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
        return factory;
    }
}
