package com.example.poc.status_event;

public record StatusEvent(int batch, int index, String timestamp, int count, long elapsedSeconds) {
}
