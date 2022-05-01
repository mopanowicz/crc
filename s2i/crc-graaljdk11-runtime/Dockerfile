FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:release

EXPOSE 8080
EXPOSE 8443

LABEL io.openshift.expose-services="8080:http,8443:https"

ENV GRAALVM_VERSION=22.1.0
ENV JAVA_VERSION=11
ENV JAVA_HOME=/usr/local/graalvm-ce-java${JAVA_VERSION}-${GRAALVM_VERSION}

USER root

COPY ./root /

RUN cd /usr/local && \
    wget -q https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAALVM_VERSION}/graalvm-ce-java${JAVA_VERSION}-linux-amd64-${GRAALVM_VERSION}.tar.gz && \
    tar xzf graalvm-ce-java${JAVA_VERSION}-linux-amd64-${GRAALVM_VERSION}.tar.gz && \
    rm graalvm-ce-java${JAVA_VERSION}-linux-amd64-${GRAALVM_VERSION}.tar.gz && \
    ln -s $JAVA_HOME/bin/java -t /usr/bin && \
    ln -s $JAVA_HOME/bin/keytool -t /usr/bin

USER 1001

ENTRYPOINT ["container-entrypoint"]
