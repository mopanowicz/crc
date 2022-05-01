# crc-graaljdk11-runtime

```bash
oc new-build -n openshift --source-secret crc-github https://mopanowicz@github.com/mopanowicz/crc.git --context-dir s2i/crc-graaljdk11-runtime --name crc-graaljdk11-runtime --to='crc-graaljdk11-runtime:release'
```
