FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-graaljdk17-runtime:latest

ENV MAVEN_VERSION=3.9.5

LABEL io.openshift.s2i.scripts-url="image:///usr/libexec/s2i"

USER root

COPY ./root /

SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN curl -sSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
      | tar xzf - -C /usr/share && \
    ln -s /usr/share/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

RUN microdnf -y install --setopt=tsflags=nodocs gcc-c++ glibc-devel zlib-devel libstdc++-devel libstdc++-static && \
    microdnf -y clean all

USER 1001

CMD [ "/usr/libexec/s2i/usage" ]
