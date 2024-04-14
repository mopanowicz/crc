# crc-openjdk17-kafka-connect-runtime

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-openjdk17-kafka-connect-runtime --source-secret=crc-github --name=crc-openjdk17-kafka-connect-runtime --to=crc-openjdk17-kafka-connect-runtime:release
```

Local build

```bash
docker build --tag openshift/crc-openjdk17-kafka-connect-runtime:release .
```