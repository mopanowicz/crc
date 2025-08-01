# notes

```bash
export LOGGING_KAFKA_PROPERTIES_BOOTSTRAP_SERVERS=kafka.local:9092
```

```bash
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --create --topic log-events --config retention.ms=30000 --config delete.retention.ms=40000
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --describe --topic log-events
./bin/kafka-console-consumer.sh  --bootstrap-server kafka.local:9092 --topic log-events
```