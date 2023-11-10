# crc-nginx

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-nginx  --source-secret=crc-github --name=crc-nginx --to=crc-nginx:release
```