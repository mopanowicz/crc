# crc-openjdk17-builder

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-openjdk17-builder --source-secret=crc-github --name=crc-openjdk17-builder --to=crc-openjdk17-builder:release
```

Local build

```bash
docker build --tag openshift/crc-openjdk17-builder:release .
```

Template

```bash
oc apply -n openshift -f openshift/crc-openjdk17-dual.yml
```
