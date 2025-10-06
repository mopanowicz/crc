#!/bin/bash

# run within buildah unsharez   

microcontainer=$(buildah from rockylinux/rockylinux:8-ubi-micro)
micromount=$(buildah mount $microcontainer)

buildah config --env HOME=/app --env VERSION_TAG=latest ${microcontainer}

dnf -y update --installroot $micromount --releasever 8 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False
dnf -y install --installroot $micromount --releasever 8 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
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
    zip

dnf -y install --installroot $micromount --releasever 8 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
    shadow-utils \

buildah run ${microcontainer} -- useradd -u 1001 -g 1001 -d /app -m -s /sbin/nologin default
buildah run ${microcontainer} -- chmod a+rwx /app

dnf -y remove --installroot $micromount --releasever 8 --nogpgcheck shadow-utils
dnf -y clean all --installroot $micromount --releasever 8 --nogpgcheck

buildah umount $microcontainer
buildah commit $microcontainer crc-ubi8-micro
