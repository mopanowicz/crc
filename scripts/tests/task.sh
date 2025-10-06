#!/bin/bash

TASK_OUT=${TASK_OUT:-task.out}

echo "task $*"
echo $? > $TASK_OUT
