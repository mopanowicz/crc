FROM rockylinux:9-minimal

ARG VERSION_TAG=latest

ENV VERSION_TAG=${VERSION_TAG}

ENV APP_UID=1001
ENV APP_GID=0
ENV HOME=/opt/app

USER root

COPY ./root /

RUN microdnf -y update --setopt=tsflags=nodocs && \
    microdnf -y install --setopt=tsflags=nodocs \
      curl \
      file \
      findutils \
      gzip \
      iputils \
      openssh-clients \
      openssl \
      procps \
      tar \
      which \
      xz \
      zip \
      && \
    \
    microdnf -y install --setopt=tsflags=nodocs shadow-utils && \
    mkdir -p ${HOME} && \
    useradd -r -u ${APP_UID} -g ${APP_GID} -d ${HOME} -s /sbin/nologin default && \
    fix-permissions ${HOME} && \
    microdnf -y remove shadow-utils && \
    microdnf -y clean all

USER ${APP_UID}

WORKDIR ${HOME}
