# crc-graaljdk11-builder

```bash
oc new-build -n openshift --source-secret crc-github https://mopanowicz@github.com/mopanowicz/crc.git --context-dir s2i/crc-graaljdk11-builder --name crc-graaljdk11-builder --to='crc-graaljdk11-builder:release'
```

Template

```bash
oc apply -n openshift -f openshift/crc-graaljdk11-dual.yml
```