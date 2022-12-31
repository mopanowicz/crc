# crc-graaljdk17-runtime

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-graaljdk17-runtime --source-secret=crc-github --name=crc-graaljdk17-runtime --to=crc-graaljdk17-runtime:release
```
