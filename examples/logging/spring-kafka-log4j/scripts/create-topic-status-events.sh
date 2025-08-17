#!/usr/bin/env bash

source common.sh

export TOPIC=status-events-log4j

"${KAFKA_CLIENT_ROOT}"/bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --delete --topic ${TOPIC}
"${KAFKA_CLIENT_ROOT}"/bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --create --topic ${TOPIC} \
  --config cleanup.policy=compact \
  --config min.compaction.lag.ms=10 \
  --config max.compaction.lag.ms=100 \
  --config delete.retention.ms=100
#   \
#  --config segment.ms=100 \
#  --config min.cleanable.dirty.ratio=0.01
"${KAFKA_CLIENT_ROOT}"/bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --describe --topic ${TOPIC}
#"${KAFKA_CLIENT_ROOT}"/bin/kafka-console-consumer.sh  --bootstrap-server ${BOOTSTRAP_SERVER} --topic ${TOPIC}
