#!/bin/bash

# run within buildah unsharez   

set -e

microcontainer=$(buildah from rockylinux/rockylinux:8-ubi-micro)
micromount=$(buildah mount ${microcontainer})

_VERSION_TAG=${_VERSION_TAG:-latest}
_HOME=/app

buildah config --env HOME=${_HOME} --env VERSION_TAG=${_VERSION_TAG} ${microcontainer}

buildah copy ${microcontainer} ./root/ /

dnf -y update --installroot ${micromount} --releasever 8 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False
dnf -y install --installroot ${micromount} --releasever 8 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
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

dnf -y install --installroot ${micromount} --releasever 8 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
    shadow-utils \

buildah run ${microcontainer} -- groupadd -g 1001 default
buildah run ${microcontainer} -- useradd -u 1001 -g 1001 -d ${_HOME} -m -s /sbin/nologin default
buildah run ${microcontainer} -- chmod a+rwx ${_HOME}

dnf -y remove --installroot ${micromount} --releasever 8 --nogpgcheck shadow-utils
dnf -y clean all --installroot ${micromount} --releasever 8 --nogpgcheck

buildah config --user 1001 ${microcontainer}

buildah config --workingdir ${_HOME} ${microcontainer}

buildah umount ${microcontainer}
buildah commit ${microcontainer} micro/crc-ubi8
