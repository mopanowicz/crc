FROM openshift/crc-base:latest

EXPOSE 8080
EXPOSE 8443

ENV JAVA_VERSION=25
ENV JAVA_HOME="/usr/local/graalvm-jdk-${JAVA_VERSION}"

ENV PATH="${PATH}:${JAVA_HOME}/bin"

USER root

COPY ./root /

RUN curl -L https://download.oracle.com/graalvm/${JAVA_VERSION}/latest/graalvm-jdk-${JAVA_VERSION}_linux-x64_bin.tar.gz \
            | tar xzf - -C /usr/local && \
    ln -s $(find /usr/local -name "graalvm-jdk-${JAVA_VERSION}*") ${JAVA_HOME}

USER ${APP_UID}

ENTRYPOINT ["container-entrypoint"]
