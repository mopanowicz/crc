#!/usr/bin/env bash

source common.sh

export TOPIC=${TOPIC:-trigger-events-log4j}

batch_file=trigger-event.log
batch_begin=${batch_begin:-1}
batch_end=${batch_end:-100}
batch_size=${batch_size:-10000}

for batch in $(seq $batch_begin $batch_end); do
  echo "Producing ${batch_size} events for batch ${batch} to file ${batch_file}"
  python produce-trigger-event-log.py $batch $batch_size > ${batch_file}

  echo "Sending file ${batch_file} to ${TOPIC} on ${BOOTSTRAP_SERVER}"
  cat ${batch_file} |
    "${KAFKA_CLIENT_ROOT}"/bin/kafka-console-producer.sh --bootstrap-server "${BOOTSTRAP_SERVER}" --topic "${TOPIC}" \
      --property "parse.key=true" \
      --property "key.separator=:" \
      --property "parse.headers=true" \
      --property "headers.delimiter=;"
done
