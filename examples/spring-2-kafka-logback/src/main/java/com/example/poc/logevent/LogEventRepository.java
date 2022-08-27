package com.example.poc.logevent;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogEventRepository extends MongoRepository<LogEvent, String> {
}
