#!/bin/bash

# run within buildah unshare

set -e

microcontainer=$(buildah from rockylinux/rockylinux:9-ubi-micro)
echo "microcontainer=${microcontainer}"
if [[ "$microcontainer" == "" ]]; then
    echo "Error creating microcontainer - exiting"
    exit 1
fi

micromount=$(buildah mount ${microcontainer})
echo "micromount=${micromount}"
if [[ "$micromount" == "" ]]; then
    echo "Error creating micromount - exiting"
    exit 1
fi

_VERSION_TAG=${_VERSION_TAG:-latest}
_APP_UID=1001
_APP_GID=0
_HOME=/app

buildah config \
    --env VERSION_TAG=${_VERSION_TAG} \
    --env APP_UID=${_APP_UID} \
    --env APP_GID=${_APP_GID} \
    --env HOME=${_HOME} \
    ${microcontainer}

buildah copy ${microcontainer} ./root/ /

dnf -y update --installroot ${micromount} --releasever 9 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False
dnf -y install --installroot ${micromount} --releasever 9 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
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
    zip

dnf -y install --installroot ${micromount} --releasever 9 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
    shadow-utils \

buildah run ${microcontainer} -- useradd -r -u ${_APP_UID} -g ${_APP_GID} -d ${_HOME} -m -s /sbin/nologin default
buildah run ${microcontainer} -- chmod a+rwx ${_HOME}

dnf -y remove --installroot ${micromount} --releasever 9 --nogpgcheck shadow-utils
dnf -y clean all --installroot ${micromount} --releasever 9 --nogpgcheck

buildah config --user ${_APP_UID} ${microcontainer}

buildah config --workingdir ${_HOME} ${microcontainer}

buildah umount ${microcontainer}
buildah commit ${microcontainer} micro/crc-base
