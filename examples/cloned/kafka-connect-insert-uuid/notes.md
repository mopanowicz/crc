# notes

```bash
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic connect-offsets
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic connect-configs
./bin/kafka-topics.sh --bootstrap-server kafka.local:9092 --delete --topic connect-status
```

```bash
./bin/kafka-topics.sh --create --topic connect-offsets --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
./bin/kafka-topics.sh --create --topic connect-configs --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
./bin/kafka-topics.sh --create --topic connect-status --config 'cleanup.policy=compact' --bootstrap-server kafka.local:9092
```

```bash
./bin/connect-distributed.sh config/connect-distributed.properties
```
