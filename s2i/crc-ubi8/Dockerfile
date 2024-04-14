FROM rockylinux:8-minimal

ARG VERSION_TAG=latest

ENV HOME=/app
ENV VERSION_TAG=${VERSION_TAG}

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
    microdnf -y install --setopt=tsflags=nodocs \
      mc \
      shadow-utils \
      && \
    useradd -u 1001 -d ${HOME} -m -s /sbin/nologin default && \
    chmod a+rwx ${HOME} && \
    microdnf -y remove shadow-utils && \
    microdnf -y clean all

USER 1001

WORKDIR ${HOME}

ENTRYPOINT ["container-entrypoint"]
