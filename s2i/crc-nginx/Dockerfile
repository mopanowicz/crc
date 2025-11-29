FROM openshift/crc-base:latest

EXPOSE 8080
EXPOSE 8443

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs nginx && \
    microdnf -y clean all

USER ${APP_UID}

ENTRYPOINT ["container-entrypoint"]
