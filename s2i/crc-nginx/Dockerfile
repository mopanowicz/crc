FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:release

EXPOSE 8080
EXPOSE 8443

LABEL io.openshift.expose-services="8080:http,8443:https"

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs nginx && \
    microdnf -y clean all

USER 1001

ENTRYPOINT ["container-entrypoint"]
