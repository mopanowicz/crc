FROM openshift/crc-openjdk17-runtime:release

ENV MAVEN_VERSION="3.9.6"
ENV GRADLE_VERSION="8.7"
ENV MAVEN_HOME="/usr/share/maven"
ENV GRADLE_HOME="/usr/share/gradle"

LABEL io.openshift.s2i.scripts-url="image:///usr/libexec/s2i"

USER root

COPY ./root /

SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN microdnf -y install --setopt=tsflags=nodocs "java-${JAVA_VERSION}-openjdk-devel" && \
    \
	curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar xzf - -C /usr/share && \
	mv /usr/share/apache-maven-${MAVEN_VERSION} ${MAVEN_HOME} && \
	ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn && \
	\
	wget -q https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip && \
	unzip -q gradle-${GRADLE_VERSION}-bin.zip -d /usr/share && \
	rm -f gradle-${GRADLE_VERSION}-bin.zip && \
	mv /usr/share/gradle-${GRADLE_VERSION} ${GRADLE_HOME} && \
	ln -s ${GRADLE_HOME}/bin/gradle /usr/bin/gradle && \
    \
    microdnf -y clean all

USER 1001

CMD [ "/usr/libexec/s2i/usage" ]
