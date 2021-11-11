FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-ubi8:latest

EXPOSE 8080
EXPOSE 8443

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs nginx && \
    microdnf -y clean all

USER 1001

ENTRYPOINT ["container-entry-point"]
