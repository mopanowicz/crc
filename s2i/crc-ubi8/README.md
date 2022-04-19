# crc-ubi8

```bash
oc new-build -n openshift --source-secret crc-github https://mopanowicz@github.com/mopanowicz/crc.git --context-dir s2i/crc-ubi8 --name crc-ubi8 --to='crc-ubi8:release'
```
