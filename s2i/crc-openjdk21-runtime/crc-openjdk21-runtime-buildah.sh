#!/bin/bash

# run within buildah unsharez   

set -e

microcontainer=$(buildah from localhost/micro/crc-ubi8)
micromount=$(buildah mount $microcontainer)

_JAVA_VERSION=21
_JAVA_HOME=/usr/lib/jvm/jre

buildah config --port 8080 --port 8443 ${microcontainer}

buildah config --label io.openshift.expose-services="8080:http,8443:https" ${microcontainer}

buildah config --env JAVA_VERSION=${_JAVA_VERSION} --env JAVA_HOME=${_JAVA_HOME} ${microcontainer}

buildah copy ${microcontainer} ./root/ /

dnf -y install --installroot ${micromount} --releasever 8 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
    java-${_JAVA_VERSION}-openjdk-headless

dnf -y clean all --installroot ${micromount} --releasever 8 --nogpgcheck

buildah config --entrypoint container-entrypoint ${microcontainer}

buildah umount $microcontainer
buildah commit $microcontainer micro/crc-openjdk21-runtime
