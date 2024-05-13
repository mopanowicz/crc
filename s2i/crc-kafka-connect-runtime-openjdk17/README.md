# crc-kafka-connect-runtime-openjdk17

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-kafka-connect-runtime-openjdk17 --source-secret=crc-github --name=crc-kafka-connect-runtime-openjdk17 --to=crc-kafka-connect-runtime-openjdk17:release
```

Local build

```bash
docker build --tag openshift/crc-kafka-connect-runtime-openjdk17:release .
```