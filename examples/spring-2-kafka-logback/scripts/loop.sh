#!/bin/bash

address=${1:-http://localhost:8084}
scope=${2:-shell}
iterations=${3:-10}
sleep_seconds=${4:-0}

echo "address $address"
echo "scope $scope"
echo "iterations $iterations"


for ((i=1; i<=iterations; i++)); do
    echo "i=$i"
    curl "$address/count?scope=$scope&iteration=$i"
    echo ""
    if [ $sleep_seconds -gt 0 ]; then
        echo "sleep $sleep_seconds"
        sleep $sleep_seconds
    fi
done
