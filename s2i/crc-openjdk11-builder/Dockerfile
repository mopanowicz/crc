FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-openjdk11-runtime:release

ARG RUNTIME_VERSION_TAG=latest

ENV MAVEN_VERSION=3.9.0
ENV RUNTIME_VERSION_TAG=${RUNTIME_VERSION_TAG}

LABEL io.openshift.s2i.scripts-url="image:///usr/libexec/s2i"

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs "java-${JAVA_VERSION}-openjdk-devel" && \
    microdnf -y clean all

SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
      | tar xzf - -C /usr/share && \
    ln -s /usr/share/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

USER 1001

CMD [ "/usr/libexec/s2i/usage" ]
