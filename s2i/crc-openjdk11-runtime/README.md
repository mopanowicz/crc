# crc-openjdk11-runtime

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-openjdk11-runtime --source-secret=crc-github --name=crc-openjdk11-runtime --to=crc-openjdk11-runtime:release
```
