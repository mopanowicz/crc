#!/bin/bash

# run within buildah unsharez   

set +e

microcontainer=$(buildah from localhost/micro/crc-ubi8)
micromount=$(buildah mount ${microcontainer})

_JAVA_VERSION=21
_MAVEN_VERSION=3.9.9

buildah config --env MAVEN_VERSION=${_MAVEN_VERSION} ${microcontainer}

buildah config --label io.openshift.s2i.scripts-url="image:///usr/libexec/s2i" ${microcontainer}

buildah copy ${microcontainer} ./root/ /

dnf -y install --installroot ${micromount} --releasever 8 --nogpgcheck --setopt=tsflags=nodocs --setopt=install_weak_deps=False \
    java-${_JAVA_VERSION}-openjdk-devel

_MAVEN_TAR_GZ=apache-maven-${_MAVEN_VERSION}-bin.tar.gz

buildah config --user root ${microcontainer}

buildah run ${microcontainer} -- curl -o ${_MAVEN_TAR_GZ} https://archive.apache.org/dist/maven/maven-3/${_MAVEN_VERSION}/binaries/apache-maven-${_MAVEN_VERSION}-bin.tar.gz
buildah run ${microcontainer} -- tar xzf ${_MAVEN_TAR_GZ} -C /usr/local
buildah run ${microcontainer} -- rm ${_MAVEN_TAR_GZ}
buildah run ${microcontainer} -- ln -s /usr/local/apache-maven-${_MAVEN_VERSION}/bin/mvn /usr/bin/mvn

dnf -y clean all --installroot ${micromount} --releasever 8 --nogpgcheck

buildah config --entrypoint /usr/libexec/s2i/usage ${microcontainer}

buildah config --user 1001 ${microcontainer}

buildah umount ${microcontainer}
buildah commit ${microcontainer} micro/crc-openjdk21-builder
