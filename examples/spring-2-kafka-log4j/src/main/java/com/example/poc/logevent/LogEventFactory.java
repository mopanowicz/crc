package com.example.poc.logevent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "logging.event")
public class LogEventFactory {

    Map<String, String> labels;
    String namespace;
    Map<String, String> pod;
    Map<String, String> container;

    public LogEvent logEvent(String loggerName, String level, String message) {
        Thread ct = Thread.currentThread();
        LogEvent event = LogEvent.builder()
                .timestamp(new Timestamp(System.currentTimeMillis()).toString())
                .kubernets(Kubernetes.builder()
                        .namespace(namespace)
                        .pod(pod)
                        .container(container)
                        .namespaceLabels(labels)
                        .build())
                .log(Log.builder()
                        .loggerName(loggerName)
                        .thread(ct.getName())
                        .level(level)
                        .message(message)
                        .build())
                .build();
        return event;
    }
}
