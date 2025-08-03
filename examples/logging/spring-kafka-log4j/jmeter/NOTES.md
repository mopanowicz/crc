# Notes

Point ```JM_LAUNCH``` to java 11 executable.

```bash
./jmeter-log-test.sh http localhost 8082 local-log4j 10 3000
./jmeter-log-test.sh http localhost 8082 local-log4j 100 300
./jmeter-log-test.sh http localhost 8082 local-log4j 200 150
./jmeter-log-test.sh http localhost 8082 local-log4j 300 100
```

```bash
./jmeter-log-test.sh http localhost 8084 local-logback 10 3000
./jmeter-log-test.sh http localhost 8084 local-logback 100 300
./jmeter-log-test.sh http localhost 8084 local-logback 200 150
./jmeter-log-test.sh http localhost 8084 local-logback 300 100
```
