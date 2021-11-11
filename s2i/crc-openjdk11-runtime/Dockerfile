FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:latest

EXPOSE 8080
EXPOSE 8443

LABEL io.openshift.expose-services="8080:http,8443:https"

ENV JAVA_VERSION=11 \
    JAVA_HOME=/usr/lib/jvm/jre

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs java-${JAVA_VERSION}-openjdk-headless && \
    microdnf -y clean all

USER 1001

ENTRYPOINT ["container-entry-point"]
