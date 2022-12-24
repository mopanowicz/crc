# crc-openjdk11-builder

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-openjdk11-builder --source-secret=crc-github --name=crc-openjdk11-builder --to='crc-openjdk11-builder:release'
```

Template

```bash
oc apply -n openshift -f openshift/crc-openjdk11-dual.yml
```
