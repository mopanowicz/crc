# crc-graalvm-jdk25-builder

```bash
oc new-build -n openshift https://github.com/mopanowicz/crc.git --context-dir=s2i/crc-graalvm-jdk25-builder --source-secret=crc-github --name=crc-graalvm-jdk25-builder --to=crc-graalvm-jdk25-builder:latest
```

Local build

```bash
podman build --tag openshift/crc-graalvm-jdk25-builder:latest .
```

Template

```bash
oc apply -n openshift -f openshift/crc-graalvm-jdk25.yml
oc apply -n openshift -f openshift/crc-graalvm-jdk25-native.yml
```
