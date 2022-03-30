FROM registry.access.redhat.com/ubi8/ubi-minimal:latest

ENV HOME=/app

USER root

COPY ./root /

RUN microdnf -y update && \
    microdnf -y install --setopt=tsflags=nodocs curl findutils procps openssl && \
    \
    microdnf -y install --setopt=tsflags=nodocs shadow-utils && \
    useradd -u 1001 -d ${HOME} -m -s /sbin/nologin default && \
    chmod 777 ${HOME} && \
    microdnf -y remove shadow-utils && \
    # temporary \
    microdnf -y install --setopt=tsflags=nodocs vim which gzip tar && \
    \
    microdnf -y clean all

USER 1001

WORKDIR ${HOME}

ENTRYPOINT ["container-entrypoint"]
