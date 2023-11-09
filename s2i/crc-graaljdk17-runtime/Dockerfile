FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:release

EXPOSE 8080
EXPOSE 8443

LABEL io.openshift.expose-services="8080:http,8443:https"

ENV JDK_VERSION=17.0.9
ENV JAVA_VERSION=17
ENV JAVA_HOME=/usr/local/graalvm-community-openjdk-17.0.9+9.1

ENV PATH="${PATH}:${JAVA_HOME}/bin"

USER root

COPY ./root /

RUN GRAALVM_TAR_GZ=graalvm-community-jdk-${JDK_VERSION}_linux-x64_bin.tar.gz && \
    curl -sSLO https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-${JDK_VERSION}/${GRAALVM_TAR_GZ} && \
    tar xzf ${GRAALVM_TAR_GZ} -C /usr/local && \
    rm ${GRAALVM_TAR_GZ}

USER 1001

ENTRYPOINT ["container-entrypoint"]
