```bash
pack build demo-api-spring --builder paketobuildpacks/builder:base
```

```bash
pack build localhost:5000/buildpack-examples/demo-api-spring \
    --builder paketobuildpacks/builder:base \
    --path . \
    --cache-image localhost:5000/buildpack-examples/maven-cache-image:latest \
    --network host \
    --publish
```

sh-4.4$ cat import-tls-crt.sh 
#!/bin/sh

FNAME_PREFIX=cert

KEYTOOL=$(which keytool)
if [ "$KEYTOOL" == "" ]; then
  KEYTOOL=$(find /layers -name "keytool")
fi

TRUSTSTORE=/etc/pki/java/cacerts

csplit -s -z -f $FNAME_PREFIX /svc-tls/tls.crt '/-----BEGIN CERTIFICATE-----/' '{*}'

for i in `ls ${FNAME_PREFIX}*`; do
  echo $i
done
