# crc-base

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git#main --context-dir=s2i/crc-base --source-secret=crc-github --name=crc-base --to=crc-base:latest
```

Local build

```bash
podman build --tag openshift/crc-base:latest .
```