FROM registry.access.redhat.com/ubi8/ubi-minimal:latest

ENV HOME=/app

USER root

COPY ./root /

RUN microdnf -y update && \
    microdnf -y install --setopt=tsflags=nodocs \
      curl \
      findutils \
      gzip \
      openssl \
      procps \
      tar \
      vim \
      which \
      && \
    \
    microdnf -y install --setopt=tsflags=nodocs shadow-utils && \
    useradd -u 1001 -d ${HOME} -m -s /sbin/nologin default && \
    microdnf -y remove shadow-utils && \
    microdnf -y clean all

USER 1001

WORKDIR ${HOME}

ENTRYPOINT ["container-entrypoint"]
