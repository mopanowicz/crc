# crc-native-runtime

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-native-runtime --source-secret=crc-github --name=crc-native-runtime --to=crc-native-runtime:latest
```
