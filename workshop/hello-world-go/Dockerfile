FROM golang:alpine

COPY src/ /app/

ENV MESSAGE "Hello from Dockerfile"
ENV HOME /app

RUN chgrp -R 0 /app && chmod -R g+rwX /app

EXPOSE 8080

USER 1001

WORKDIR $HOME

CMD go run hello-world.go
