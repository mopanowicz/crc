#!/bin/bash

npm install

ng build --base-href /loop-timer/ --configuration production

rm -rf /usr/share/nginx/html/loop-timer

mkdir -p /usr/share/nginx/html/loop-timer

cp dist/loop-timer/browser/* /usr/share/nginx/html/loop-timer

echo "done"
