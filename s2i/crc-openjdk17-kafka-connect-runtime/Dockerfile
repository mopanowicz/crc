FROM openshift/crc-openjdk17-runtime:release

USER root

COPY ./root /

ENV KAFKA_VERSION=3.6.1
ENV SCALA_VERSION=2.13

RUN KAFKA=kafka_${SCALA_VERSION}-${KAFKA_VERSION} && \
    KAFKA_TGZ=${KAFKA}.tgz && \
    echo "KAFKA_TGZ=${KAFKA_TGZ}" && \
    \
    curl https://archive.apache.org/dist/kafka/${KAFKA_VERSION}/${KAFKA_TGZ} -o ${KAFKA_TGZ} && \
    tar xzf ${KAFKA_TGZ} && \
    ln -s ${KAFKA} kafka && \
    cp kafka/config/connect-distributed.properties kafka/config/connect-distributed.properties.orig && \
    rm ${KAFKA_TGZ} && \
    mkdir ${HOME}/plugins && chmod a+rwx ${HOME}/plugins && \
    mkdir ${HOME}/kafka/logs && chmod a+rwx ${HOME}/kafka/logs

RUN mkdir ${HIME}/ext && \
    curl -o confluentinc-kafka-connect-jdbc-10.7.6.zip https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-jdbc/versions/10.7.6/confluentinc-kafka-connect-jdbc-10.7.6.zip && \
    unzip -j confluentinc-kafka-connect-jdbc-10.7.6.zip -d ${HOME}/ext && \
    rm confluentinc-kafka-connect-jdbc-10.7.6.zip && \
    curl -o confluentinc-kafka-connect-avro-converter-7.6.1.zip https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-avro-converter/versions/7.6.1/confluentinc-kafka-connect-avro-converter-7.6.1.zip && \
    unzip -oj confluentinc-kafka-connect-avro-converter-7.6.1.zip -d ${HOME}/ext && \
    rm  confluentinc-kafka-connect-avro-converter-7.6.1.zip

COPY ./root /

USER 1001
