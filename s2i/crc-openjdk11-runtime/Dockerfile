FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:release

EXPOSE 8080
EXPOSE 8443

LABEL io.openshift.expose-services="8080:http,8443:https"

ENV BASE_OPENSHIFT_BUILD_NAME=${OPENSHIFT_BUILD_NAME}
ENV BASE_OPENSHIFT_BUILD_NAMESPACE=${OPENSHIFT_BUILD_NAMESPACE}
ENV BASE_OPENSHIFT_BUILD_SOURCE=${OPENSHIFT_BUILD_SOURCE}
ENV BASE_OPENSHIFT_BUILD_REFERENCE=${OPENSHIFT_BUILD_REFERENCE}
ENV BASE_OPENSHIFT_BUILD_COMMIT=${OPENSHIFT_BUILD_COMMIT}

ENV JAVA_VERSION=11
ENV JAVA_HOME=/usr/lib/jvm/jre

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs java-${JAVA_VERSION}-openjdk-headless && \
    microdnf -y clean all && \
    \
    chmod a+w /etc/pki/ca-trust/extracted/java/cacerts

USER 1001

ENTRYPOINT ["container-entrypoint"]
