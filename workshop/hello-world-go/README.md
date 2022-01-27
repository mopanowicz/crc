# hello-world (go)

## Build

```bash
podman build -t hello-world .
```

## Run

```bash
podman run -it -p 8080:8080 hello-world

podman run -it -p 8080:8080 --env MESSAGE='Hello from the host' hello-world
```
