# crc-openjdk11-builder

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git#main --context-dir=s2i/crc-openjdk11-builder --source-secret=crc-github --name=crc-openjdk11-builder --to=crc-openjdk11-builder:release
```

```bash
oc start-build -n openshift crc-openjdk11-builder --build-arg BUILDER_VERSION_TAG=beta
```

Template

```bash
oc apply -n openshift -f openshift/crc-openjdk11-dual.yml
```
