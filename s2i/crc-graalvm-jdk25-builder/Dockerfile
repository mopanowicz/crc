FROM openshift/crc-base:latest

ENV MAVEN_VERSION=3.9.11
ENV GRADLE_VERSION=9.2.1

ENV JAVA_VERSION=25
ENV JAVA_HOME="/usr/local/graalvm-jdk-${JAVA_VERSION}"

ENV PATH="${PATH}:${JAVA_HOME}/bin"

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs \
        gcc-c++ \
        glibc-devel \
        libstdc++-devel \
        zlib-devel \
        && \
    microdnf -y clean all

RUN curl -L https://download.oracle.com/graalvm/${JAVA_VERSION}/latest/graalvm-jdk-${JAVA_VERSION}_linux-x64_bin.tar.gz \
            | tar xzf - -C /usr/local && \
    ln -s $(find /usr/local -name "graalvm-jdk-${JAVA_VERSION}*") ${JAVA_HOME}

RUN curl -L https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
            | tar xzf - -C /usr/local && \
    ln -s /usr/local/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

RUN ARCHIVE=gradle-${GRADLE_VERSION}-all.zip && \
    curl -LO https://github.com/gradle/gradle-distributions/releases/download/v${GRADLE_VERSION}/${ARCHIVE} && \
    unzip -q ${ARCHIVE} -d /usr/local && \
    rm ${ARCHIVE} && \
    ln -s /usr/local/gradle-${GRADLE_VERSION}}/bin/bin/gradle /usr/bin/gradle

USER ${APP_UID}

CMD [ "/usr/libexec/s2i/usage" ]
