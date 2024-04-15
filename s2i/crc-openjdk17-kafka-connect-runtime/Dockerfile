FROM openshift/crc-openjdk17-runtime:release

USER root

COPY ./root /

RUN KAFKA_VERSION=3.6.2 && \
    SCALA_VERSION=2.13 && \
    KAFKA=kafka_${SCALA_VERSION}-${KAFKA_VERSION} && \
    KAFKA_TGZ=${KAFKA}.tgz && \
    \
    curl https://downloads.apache.org/kafka/${KAFKA_VERSION}/${KAFKA_TGZ} -o ${KAFKA_TGZ} && \
    tar xzf ${KAFKA_TGZ} && \
    ln -s ${KAFKA} kafka && \
    cp kafka/config/connect-distributed.properties kafka/config/connect-distributed.properties.orig && \
    rm ${KAFKA_TGZ} && \
    mkdir ${HOME}/plugins && chmod a+rwx ${HOME}/plugins && \
    mkdir ${HOME}/kafka/logs && chmod a+rwx ${HOME}/kafka/logs

USER 1001
