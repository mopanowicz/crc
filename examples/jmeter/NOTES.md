## Quarkus

```bash
./mvnw clean package -Dquarkus.package.type=uber-jar
java -Xmx1024M -jar target/*-runner.jar
```

## Spring Boot

```bash
./mvnw clean package
java -Xmx1024M -jar target/demo-api-spring-0.0.1.jar --spring.profiles.active=dev
```
