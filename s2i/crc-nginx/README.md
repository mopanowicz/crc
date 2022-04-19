# crc-nginx

```bash
oc new-build -n openshift --source-secret crc-github https://mopanowicz@github.com/mopanowicz/crc.git --context-dir s2i/crc-nginx --name crc-nginx --to='crc-nginx:release'
```
