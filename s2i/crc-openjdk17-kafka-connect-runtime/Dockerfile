FROM openshift/crc-openjdk17-runtime:release

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs \
      wget \
    && \
    microdnf -y clean all && \
    \
    KAFKA_VERSION=3.6.2 && \
    SCALA_VERSION=2.13 && \
    KAFKA=kafka_${SCALA_VERSION}-${KAFKA_VERSION} && \
    KAFKA_TGZ=${KAFKA}.tgz && \
    wget https://downloads.apache.org/kafka/${KAFKA_VERSION}/${KAFKA_TGZ} && \
    tar xzf ${KAFKA_TGZ} && \
    ln -s ${KAFKA} kafka && \
    cp kafka/config/connect-distributed.properties kafka/config/connect-distributed.properties.orig && \
    rm ${KAFKA_TGZ}

USER 1001

WORKDIR ${HOME}/kafka
