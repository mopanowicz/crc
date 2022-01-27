FROM golang:alpine

COPY src/ /app/

ENV MESSAGE "Hello from Dockerfile"
ENV HOME /app

# Set permissions to the /app folder (for OpenShift)
RUN chgrp -R 0 /app && chmod -R g+rwX /app

EXPOSE 8080

# OpenShift picks up this label and creates a service
LABEL io.openshift.expose-services 8080/http

# OpenShift uses root group instead of root user
USER 1001

WORKDIR $HOME

CMD go run hello-world.go
