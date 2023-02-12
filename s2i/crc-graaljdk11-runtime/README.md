# crc-graaljdk11-runtime

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git#master --context-dir=s2i/crc-graaljdk11-runtime --source-secret=crc-github --name=crc-graaljdk11-runtime --to='crc-graaljdk11-runtime:release'
```
