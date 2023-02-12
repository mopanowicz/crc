# crc-ubi8

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git#main --context-dir=s2i/crc-ubi8  --source-secret=crc-github --name=crc-ubi8 --to=crc-ubi8:release
```
