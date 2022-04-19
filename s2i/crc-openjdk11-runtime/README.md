# crc-openjdk11-runtime

```bash
oc new-build -n openshift --source-secret crc-github https://mopanowicz@github.com/mopanowicz/crc.git --context-dir s2i/crc-openjdk11-runtime --name crc-openjdk11-runtime --to='crc-openjdk11-runtime:release'
```
