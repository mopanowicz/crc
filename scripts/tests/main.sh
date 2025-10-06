#!/bin/bash

export TASK_OUT=task.out

scriptdir=$(dirname $0)

${scriptdir}/task.sh 1 2 3

time ${scriptdir}/task.sh timed

timeout 1s time ${scriptdir}/task.sh with timeout
