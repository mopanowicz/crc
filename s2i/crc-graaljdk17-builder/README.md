# crc-graaljdk17-builder

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-graaljdk17-builder --source-secret=crc-github --name=crc-graaljdk17-builder --to=crc-graaljdk17-builder:release
```

Template

```bash
oc apply -n openshift -f openshift/crc-graaljdk17-dual.yml
```
