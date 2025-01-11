FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:latest

EXPOSE 8080
EXPOSE 8443

LABEL io.openshift.expose-services="8080:http,8443:https"

USER root

COPY ./root /

USER 1001

ENTRYPOINT ["container-entrypoint"]
