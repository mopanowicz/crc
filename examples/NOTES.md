# notes

```sql
CREATE ROLE sonarqube WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
    PASSWORD 'sonarqube123';

CREATE DATABASE sonarqube
    WITH OWNER=sonarqube ENCODING = 'UTF8' CONNECTION LIMIT = -1;
```

pg_hba.conf

```
host    all             all             127.0.0.1/32            md5
```

```bash
export SONAR_HOST_URL="http://sonar.local:9000"
export SONAR_SCANNER_OPTS="-Dsonar.projectKey=demo-ng -Dsonar.login=sqa_b80bcee52556a194da3b466087c5623e797c7c05"
```
