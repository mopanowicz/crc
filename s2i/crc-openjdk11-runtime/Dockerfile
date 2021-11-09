FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:latest

ENV JAVA_VERSION=11 \
    JAVA_HOME=/usr/lib/jvm/jre \
    APP_JAR=app.jar

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs java-${JAVA_VERSION}-openjdk && \
    microdnf -y clean all

USER 1001

ENTRYPOINT ["container-entry-point"]
