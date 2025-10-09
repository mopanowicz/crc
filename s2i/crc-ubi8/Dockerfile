FROM rockylinux:8-minimal

ARG VERSION_TAG=latest

ENV HOME=/app
ENV VERSION_TAG=${VERSION_TAG}

USER root

COPY ./root /

RUN microdnf -y update --setopt=tsflags=nodocs && \
    microdnf -y install --setopt=tsflags=nodocs \
      curl \
      findutils \
      glibc-langpack-en \
      glibc-langpack-pl \
      gzip \
      iputils \
      openssh-clients \
      openssl \
      procps \
      tar \
      which \
      zip \
      && \
    \
    microdnf -y install --setopt=tsflags=nodocs \
      shadow-utils \
      && \
    groupadd -g 1001 default && \
    useradd -u 1001 -g 1001 -d ${HOME} -m -s /sbin/nologin default && \
    chmod a+rwx ${HOME} && \
    microdnf -y remove shadow-utils && \
    microdnf -y clean all

USER 1001

WORKDIR ${HOME}
