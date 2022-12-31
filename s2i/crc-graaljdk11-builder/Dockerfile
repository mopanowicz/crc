FROM image-registry.openshift-image-registry.svc:5000/openshift/crc-graaljdk11-runtime:release

ENV MAVEN_VERSION=3.8.6

LABEL io.openshift.s2i.scripts-url="image:///usr/libexec/s2i"

USER root

COPY ./root /

RUN cd /tmp && \
    NATIVE_IMAGE_JAR="native-image-installable-svm-java${JAVA_VERSION}-linux-amd64-${GRAALVM_VERSION}.jar" && \
    curl -sSLO "https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAALVM_VERSION}/${NATIVE_IMAGE_JAR}" && \
    "$JAVA_HOME/bin/gu" -L install ${NATIVE_IMAGE_JAR} && \
    rm ${NATIVE_IMAGE_JAR}

SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN curl -sSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
      | tar xzf - -C /usr/share && \
    ln -s /usr/share/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

USER 1001

CMD [ "/usr/libexec/s2i/usage" ]
