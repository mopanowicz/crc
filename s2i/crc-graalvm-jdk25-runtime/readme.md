# crc-graalvm-jdk25-runtime

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-graalvm-jdk25-runtime --source-secret=crc-github --name=crc-graalvm-jdk25-runtime --to=crc-graalvm-jdk25-runtime:latest
```

Local build

```bash
podman build --tag openshift/crc-graalvm-jdk25-runtime:latest .
```