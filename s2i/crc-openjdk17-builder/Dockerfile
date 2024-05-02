FROM openshift/crc-ubi8:release

ENV JAVA_VERSION=17
ENV JAVA_HOME=/usr/lib/jvm/jre

ENV MAVEN_VERSION="3.9.6"
ENV MAVEN_HOME="/usr/share/maven"

ENV GRADLE_VERSION="8.7"
ENV GRADLE_HOME="/usr/share/gradle"

LABEL io.openshift.s2i.scripts-url="image:///usr/libexec/s2i"

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs "java-${JAVA_VERSION}-openjdk-devel" && \
	microdnf -y clean all

RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz -o apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
	tar xzf apache-maven-${MAVEN_VERSION}-bin.tar.gz -C /usr/share && \
	rm -f apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
	mv /usr/share/apache-maven-${MAVEN_VERSION} ${MAVEN_HOME} && \
	ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn && \
	\
	curl -fsSL https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle-${GRADLE_VERSION}-bin.zip && \
	unzip -q gradle-${GRADLE_VERSION}-bin.zip -d /usr/share && \
	rm -f gradle-${GRADLE_VERSION}-bin.zip && \
	mv /usr/share/gradle-${GRADLE_VERSION} ${GRADLE_HOME} && \
	ln -s ${GRADLE_HOME}/bin/gradle /usr/bin/gradle

USER 1001

CMD [ "/usr/libexec/s2i/usage" ]
