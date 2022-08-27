#!/bin/sh

topic=${1:-log-events}
broker=${2:-kafka.virtual.box:9092}

kcat -b $broker -t $topic -C -e -q | tee $topic.txt | grep -v ^$ | wc -l
