# crc-openjdk17-runtime

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-openjdk17-runtime --source-secret=crc-github --name=crc-openjdk17-runtime --to=crc-openjdk17-runtime:release
```
