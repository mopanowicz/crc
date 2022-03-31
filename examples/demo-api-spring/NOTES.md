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
