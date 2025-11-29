FROM openshift/crc-base:latest

EXPOSE 8080
EXPOSE 8443

USER root

COPY ./root /

USER ${APP_UID}

ENTRYPOINT ["container-entrypoint"]
