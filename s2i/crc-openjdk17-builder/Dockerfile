FROM openshift/crc-ubi8:latest

ENV JAVA_VERSION=17
ENV JAVA_HOME=/usr/lib/jvm/jre

ENV MAVEN_VERSION="3.9.6"
ENV MAVEN_HOME="/usr/share/maven"

ENV GRADLE_VERSION="8.7"
ENV GRADLE_DISTRIBUTION_TYPE=bin
ENV GRADLE_HOME="/usr/share/gradle"

LABEL io.openshift.s2i.scripts-url="image:///usr/libexec/s2i"

USER root

COPY ./root /

RUN microdnf -y install --setopt=tsflags=nodocs git "java-${JAVA_VERSION}-openjdk-devel" && \
	microdnf -y clean all

RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar xzf - -C /usr/share && \
	mv /usr/share/apache-maven-${MAVEN_VERSION} ${MAVEN_HOME} && \
	ln -sf ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

RUN	GRADLE_ZIP=gradle-${GRADLE_VERSION}-${GRADLE_DISTRIBUTION_TYPE}.zip && \
	curl -fsSL https://services.gradle.org/distributions/${GRADLE_ZIP} -o ${GRADLE_ZIP} && \
	unzip -q ${GRADLE_ZIP} -d /usr/share && \
	rm ${GRADLE_ZIP} && \
	mv /usr/share/gradle-${GRADLE_VERSION} ${GRADLE_HOME} && \
	ln -sf ${GRADLE_HOME}/bin/gradle /usr/bin/gradle

RUN chown -R default ${HOME}

USER default

RUN whoami && echo $HOME && \
	gradle init wrapper \
		--gradle-version ${GRADLE_VERSION} \
		--distribution-type ${GRADLE_DISTRIBUTION_TYPE} \
		--no-validate-url --no-daemon && \
	./gradlew buildEnvironment --no-daemon && \
	rm -rf build.* settings.* gradle*

CMD [ "/usr/libexec/s2i/usage" ]
