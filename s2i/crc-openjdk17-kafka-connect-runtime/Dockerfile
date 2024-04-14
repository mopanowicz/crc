FROM openshift/crc-openjdk17-runtime:release

USER root

COPY ./root /

RUN wget https://downloads.apache.org/kafka/3.6.2/kafka_2.13-3.6.2.tgz

USER 1001
