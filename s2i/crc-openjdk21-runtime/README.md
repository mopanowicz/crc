# crc-openjdk21-runtime

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-openjdk21-runtime --source-secret=crc-github --name=crc-openjdk21-runtime --to=crc-openjdk21-runtime:latest
```

Local build

```bash
docker build --tag openshift/crc-openjdk21-runtime:latest .
```