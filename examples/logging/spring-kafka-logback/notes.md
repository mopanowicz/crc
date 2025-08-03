# notes

```bash
export LOGGING_KAFKA_PROPERTIES_BOOTSTRAP_SERVERS=kafka.local:9092
export LOGGING_KAFKA_TOPIC=events-logback

./bin/kafka-topics.sh --bootstrap-server ${LOGGING_KAFKA_PROPERTIES_BOOTSTRAP_SERVERS} --create --topic ${LOGGING_KAFKA_TOPIC} --config retention.ms=20000 --config delete.retention.ms=20000
./bin/kafka-topics.sh --bootstrap-server ${LOGGING_KAFKA_PROPERTIES_BOOTSTRAP_SERVERS} --describe --topic ${LOGGING_KAFKA_TOPIC}
./bin/kafka-console-consumer.sh  --bootstrap-server ${LOGGING_KAFKA_PROPERTIES_BOOTSTRAP_SERVERS} --topic ${LOGGING_KAFKA_TOPIC}
```
