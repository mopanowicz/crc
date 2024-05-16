# Kafka connect setup

https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-jdbc/versions/10.7.6/confluentinc-kafka-connect-jdbc-10.7.6.zip

https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-avro-converter/versions/7.6.1/confluentinc-kafka-connect-avro-converter-7.6.1.zip

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

## Debezium

### Informix

https://debezium.io/documentation/reference/stable/connectors/informix.html#informix-deploying-a-connector

https://debezium.io/documentation/reference/stable/connectors/informix.html#setting-up-informix

```bash
useradd kafkaconnect
```

```dbaccess
create user kafkaconnect with password 'kafkaconnect123';

grant connect to kafkaconnect;
grant resource to kafkaconnect;
```