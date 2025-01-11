FROM openshift/crc-openjdk17-runtime:latest

EXPOSE 8083

ENV KAFKA_VERSION=3.6.1
ENV SCALA_VERSION=2.13
ENV KAFKA_CONNECT_AVRO_CONVERTER=7.6.1
ENV KAFKA_CONNECT_JDBC=10.7.6

ENV KAFKA_CONNECT_ROOT_PATH=${HOME}/kafka
ENV KAFKA_CONNECT_CONFIG_PATH=${KAFKA_CONNECT_ROOT_PATH}/config
ENV KAFKA_CONNECT_PLUGIN_PATH=${KAFKA_CONNECT_ROOT_PATH}/libs

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs telnet net-tools && \
    microdnf -y clean all

RUN KAFKA=kafka_${SCALA_VERSION}-${KAFKA_VERSION} && \
    KAFKA_TGZ=${KAFKA}.tgz && \
    \
    curl https://archive.apache.org/dist/kafka/${KAFKA_VERSION}/${KAFKA_TGZ} -o ${KAFKA_TGZ} && \
    tar xzf ${KAFKA_TGZ} && \
    ln -s ${KAFKA} kafka && \
    mv kafka/config/connect-distributed.properties kafka/config/connect-distributed.properties.orig && \
    touch kafka/config/connect-distributed.properties && \
    rm ${KAFKA_TGZ} && \
    mkdir ${KAFKA_CONNECT_ROOT_PATH}/logs && chmod a+rwx ${KAFKA_CONNECT_ROOT_PATH}/logs

RUN curl -o confluentinc-kafka-connect-jdbc-${KAFKA_CONNECT_JDBC}.zip https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-jdbc/versions/${KAFKA_CONNECT_JDBC}/confluentinc-kafka-connect-jdbc-${KAFKA_CONNECT_JDBC}.zip && \
    unzip -oj confluentinc-kafka-connect-jdbc-${KAFKA_CONNECT_JDBC}.zip -d ${KAFKA_CONNECT_PLUGIN_PATH} && \
    rm confluentinc-kafka-connect-jdbc-${KAFKA_CONNECT_JDBC}.zip && \
    curl -o confluentinc-kafka-connect-avro-converter-${KAFKA_CONNECT_AVRO_CONVERTER}.zip https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-avro-converter/versions/${KAFKA_CONNECT_AVRO_CONVERTER}/confluentinc-kafka-connect-avro-converter-${KAFKA_CONNECT_AVRO_CONVERTER}.zip && \
    unzip -oj confluentinc-kafka-connect-avro-converter-${KAFKA_CONNECT_AVRO_CONVERTER}.zip -d ${KAFKA_CONNECT_PLUGIN_PATH} && \
    rm  confluentinc-kafka-connect-avro-converter-${KAFKA_CONNECT_AVRO_CONVERTER}.zip

RUN curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/jdbc-4.50.10.jar https://repo1.maven.org/maven2/com/ibm/informix/jdbc/4.50.10/jdbc-4.50.10.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/ifx-changestream-client-1.1.3.jar https://repo1.maven.org/maven2/com/ibm/informix/ifx-changestream-client/1.1.3/ifx-changestream-client-1.1.3.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/debezium-connector-informix-2.6.1.Final.jar https://repo1.maven.org/maven2/io/debezium/debezium-connector-informix/2.6.1.Final/debezium-connector-informix-2.6.1.Final.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/jackson-annotations-2.16.0.jar https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.16.0/jackson-annotations-2.16.0.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/jackson-core-2.16.0.jar https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.16.0/jackson-core-2.16.0.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/jackson-databind-2.16.0.jar https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.16.0/jackson-databind-2.16.0.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/jackson-datatype-jsr310-2.16.0.jar https://repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.16.0/jackson-datatype-jsr310-2.16.0.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/ifx-changestream-client-1.1.3.jar https://repo1.maven.org/maven2/com/ibm/informix/ifx-changestream-client/1.1.3/ifx-changestream-client-1.1.3.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/jdbc-4.50.10.1.jar https://repo1.maven.org/maven2/com/ibm/informix/jdbc/4.50.10.1/jdbc-4.50.10.1.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/debezium-api-2.6.1.Final.jar https://repo1.maven.org/maven2/io/debezium/debezium-api/2.6.1.Final/debezium-api-2.6.1.Final.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/debezium-connector-informix-2.6.1.Final.jar https://repo1.maven.org/maven2/io/debezium/debezium-connector-informix/2.6.1.Final/debezium-connector-informix-2.6.1.Final.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/debezium-core-2.6.1.Final.jar https://repo1.maven.org/maven2/io/debezium/debezium-core/2.6.1.Final/debezium-core-2.6.1.Final.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/debezium-storage-file-2.6.1.Final.jar https://repo1.maven.org/maven2/io/debezium/debezium-storage-file/2.6.1.Final/debezium-storage-file-2.6.1.Final.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/debezium-storage-kafka-2.6.1.Final.jar https://repo1.maven.org/maven2/io/debezium/debezium-storage-kafka/2.6.1.Final/debezium-storage-kafka-2.6.1.Final.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/bson-3.8.0.jar https://repo1.maven.org/maven2/org/mongodb/bson/3.8.0/bson-3.8.0.jar && \
    curl -o ${KAFKA_CONNECT_PLUGIN_PATH}/slf4j-api-1.7.21.jar https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.21/slf4j-api-1.7.21.jar

RUN curl -o debezium-connector-postgres-2.6.1.Final-plugin.tar.gz https://repo1.maven.org/maven2/io/debezium/debezium-connector-postgres/2.6.1.Final/debezium-connector-postgres-2.6.1.Final-plugin.tar.gz && \
    tar xzf debezium-connector-postgres-2.6.1.Final-plugin.tar.gz && \
    cp debezium-connector-postgres/*.jar ${KAFKA_CONNECT_PLUGIN_PATH} && \
    rm -rf debezium-connector-postgres-*

RUN chown default -R ${HOME}

USER 1001
