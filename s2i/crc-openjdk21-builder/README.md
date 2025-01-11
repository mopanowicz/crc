# crc-openjdk21-builder

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-openjdk21-builder --source-secret=crc-github --name=crc-openjdk21-builder --to=crc-openjdk21-builder:latest
```

Template

```bash
oc apply -n openshift -f openshift/crc-openjdk21-dual.yml
```
