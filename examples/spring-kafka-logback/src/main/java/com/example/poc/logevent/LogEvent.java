package com.example.poc.logevent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class LogEvent {
    String id;
    @JsonProperty("@timestamp")
    String timestamp;
    @JsonProperty("@topic")
    String topic;
    Kubernetes kubernets;
    Log log;
}

@Getter
@Setter
@Builder
@ToString
class Kubernetes {
    @JsonProperty("namespace_labels")
    Map<String, String> namespaceLabels;
    String namespace;
    Map<String, String> pod;
    Map<String, String> container;
}

@Getter
@Setter
@Builder
@ToString
class Log {
    @JsonProperty("logger_name")
    String loggerName;
    String thread;
    String level;
    String message;
}