#!/bin/bash

curl -X 'POST' 'http://localhost:8081/v1/items' -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"name": "string0","entityVersion": 0}'
curl -X 'POST' 'http://localhost:8081/v1/items' -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"name": "string1","entityVersion": 0}'
curl -X 'POST' 'http://localhost:8081/v1/items' -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"name": "string2","entityVersion": 0}'
curl -X 'POST' 'http://localhost:8081/v1/items' -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"name": "string3","entityVersion": 0}'
curl -X 'POST' 'http://localhost:8081/v1/items' -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"name": "string4","entityVersion": 0}'
curl -X 'POST' 'http://localhost:8081/v1/items' -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"name": "string5","entityVersion": 0}'
curl -X 'POST' 'http://localhost:8081/v1/items' -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"name": "string6","entityVersion": 0}'
curl -X 'POST' 'http://localhost:8081/v1/items' -H 'accept: */*' -H 'Content-Type: application/json' \
  -d '{"name": "string7","entityVersion": 0}'
