# notes (tls)

```bash
openssl req -newkey rsa:2048 -new -nodes -x509 -days 3650 -keyout key.pem -out cert.pem
```

```bash
export quarkus_http_ssl_certificate_files=$(pwd)/tls/cert.pem
export quarkus_http_ssl_certificate_key_files=$(pwd)/tls/key.pem
```