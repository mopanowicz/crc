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
_HOME=/app
_USER_ID=1001
_GROUP_ID=1001

buildah config \
    --env VERSION_TAG=${_VERSION_TAG} \
    --env HOME=${_HOME} \
    --env USER_ID=${_USER_ID} \
    --env GROUP_ID=${_GROUP_ID} \
    ${microcontainer}

buildah copy ${microcontainer} ./root/ /

dnf -y update --installroot ${micromount} --releasever 9 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False
dnf -y install --installroot ${micromount} --releasever 9 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
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

dnf -y install --installroot ${micromount} --releasever 9 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
    shadow-utils \

buildah run ${microcontainer} -- groupadd -g ${_GROUP_ID} default
buildah run ${microcontainer} -- useradd -u ${_USER_ID} -g ${_GROUP_ID} -d ${_HOME} -m -s /sbin/nologin default
buildah run ${microcontainer} -- chmod a+rwx ${_HOME}

dnf -y remove --installroot ${micromount} --releasever 9 --nogpgcheck shadow-utils
dnf -y clean all --installroot ${micromount} --releasever 9 --nogpgcheck

buildah config --user ${_USER_ID} ${microcontainer}

buildah config --workingdir ${_HOME} ${microcontainer}

buildah umount ${microcontainer}
buildah commit ${microcontainer} micro/crc-base
