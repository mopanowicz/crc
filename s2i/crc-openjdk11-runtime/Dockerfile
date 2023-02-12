FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:release

ARG RUNTIME_VERSION_TAG=latest

ENV RUNTIME_VERSION_TAG=${RUNTIME_VERSION_TAG}
ENV JAVA_VERSION=11
ENV JAVA_HOME=/usr/lib/jvm/jre

EXPOSE 8080
EXPOSE 8443

LABEL io.openshift.expose-services="8080:http,8443:https"

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs java-${JAVA_VERSION}-openjdk-headless && \
    microdnf -y clean all && \
    \
    chmod a+w /etc/pki/ca-trust/extracted/java/cacerts

USER 1001

ENTRYPOINT ["container-entrypoint"]
