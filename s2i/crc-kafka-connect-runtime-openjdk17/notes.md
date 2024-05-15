# Kafka connect setup

https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-${TOPIC_PREFIX}-jdbc/versions/10.7.6/confluentinc-kafka-${TOPIC_PREFIX}-jdbc-10.7.6.zip

https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-${TOPIC_PREFIX}-avro-converter/versions/7.6.1/confluentinc-kafka-${TOPIC_PREFIX}-avro-converter-7.6.1.zip

https://docs.confluent.io/platform/current/schema-registry/connect.html

```bash
TOPIC_PREFIX=connect
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic ${TOPIC_PREFIX}-offsets
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic ${TOPIC_PREFIX}-configs
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic ${TOPIC_PREFIX}-status
```

```bash
TOPIC_PREFIX=connect
./bin/kafka-topics.sh --create --topic ${TOPIC_PREFIX}-offsets --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
./bin/kafka-topics.sh --create --topic ${TOPIC_PREFIX}-configs --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
./bin/kafka-topics.sh --create --topic ${TOPIC_PREFIX}-status --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
```

```bash
./bin/connect-distributed.sh config/connect-distributed.properties
```

