#!/bin/sh

topic=${1:-log-events}
bootstraps=${2:-kafka.virtual.box:9092}

retention_bytes=1073741824

$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server ${bootstraps} --delete --topic $topic
$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server ${bootstraps} --create --topic $topic
$KAFKA_HOME/bin/kafka-configs.sh --bootstrap-server ${bootstraps} --entity-type topics --entity-name $topic --alter --add-config retention.bytes=$retention_bytes
