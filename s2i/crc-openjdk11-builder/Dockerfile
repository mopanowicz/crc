FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-openjdk11-runtime:latest

ENV MAVEN_VERSION=3.8.3

LABEL io.openshift.s2i.scripts-url="image:///usr/libexec/s2i" \
      io.openshift.expose-services="8080:http,8443:https"

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs java-${JAVA_VERSION}-openjdk-devel && \
    microdnf -y clean all

RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar xzf - -C /usr/share && \
    mv /usr/share/apache-maven-${MAVEN_VERSION} /usr/share/maven && \
    ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

USER 1001

CMD [ "/usr/libexec/s2i/usage" ]
