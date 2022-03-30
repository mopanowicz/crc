# crc-openjdk11-builder

```bash
oc new-build -n openshift --source-secret crc-github https://mopanowicz@github.com/mopanowicz/crc.git --context-dir s2i/crc-openjdk11-builder --name crc-openjdk11-builder
```

Template

```bash
oc apply -n openshift -f openshift/crc-openjdk11-dual.yml
```
