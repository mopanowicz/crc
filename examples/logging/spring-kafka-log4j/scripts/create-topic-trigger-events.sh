#!/usr/bin/env bash

source common.sh

export TOPIC=trigger-events-log4j

"${KAFKA_CLIENT_ROOT}"/bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --delete --topic ${TOPIC}
"${KAFKA_CLIENT_ROOT}"/bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --create --topic ${TOPIC} --config retention.ms=5000 --config delete.retention.ms=1000 --partitions 6
"${KAFKA_CLIENT_ROOT}"/bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --describe --topic ${TOPIC}
#"${KAFKA_CLIENT_ROOT}"/bin/kafka-console-consumer.sh  --bootstrap-server ${BOOTSTRAP_SERVER} --topic ${TOPIC}
