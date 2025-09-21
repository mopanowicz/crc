#!/usr/bin/env bash

function git_branch () {
  DEFAULT_BRANCH=$1
  BRANCH=$(git branch --show-current)
  if [[ "$BRANCH" == "" ]]; then
    prefix="refs/remotes/origin/"
    commit=$(git rev-parse HEAD)
    refs_list=$(git for-each-ref --format='%(objectname) %(refname)' $prefix | awk "/^$commit/ {print \$2}")
    while read -r line ; do BRANCH=${line#*$prefix}; done <<< $(echo "$refs_list")
  fi
  if [[ "$BRANCH" == "" ]]; then
    BRANCH=$(git rev-parse --short HEAD)
  fi
  if [[ "$BRANCH" == "" ]]; then
    BRANCH=$DEFAULT_BRANCH
  fi
  echo $BRANCH
}

function git_build_hostname () {
  echo $(hostname)
}

function git_build_time () {
  echo $(date -u +"%Y-%m-%dT%H:%M:%SZ")
}

function git_build_user_name () {
  echo $(git config user.name)
}

function git_build_user_email () {
  echo $(git config user.email)
}

function git_build_version () {
  # TODO - implement a way to get build version
  echo "0.0.0"
}

function git_closest_tag_commit_count () {
  COUNT=$(git rev-list --count $(git describe --tags --abbrev=0)..HEAD)
  echo $COUNT
}

function git_closest_tag_name () {
  TAG=$(git describe --tags --abbrev=0 2>/dev/null)
  echo $TAG
}

function git_commit_author_time () {
  COMMIT_DATE=$(git log -1 --pretty=%at)
  echo $COMMIT_DATE
}

function git_commit_commiter_time () {
  COMMIT_DATE=$(git log -1 --pretty=%ct)
  echo $COMMIT_DATE
}

function git_commit_id () {
  COMMIT_ID=$(git rev-parse HEAD)
  echo $COMMIT_ID
}

function git_commit_id_short () {
  COMMIT_ID=$(git rev-parse --short HEAD)
  echo $COMMIT_ID
}

function git_commit_id_describe () {
  COMMIT_ID=$(git describe --always --long --dirty 2>/dev/null)
  echo $COMMIT_ID
}

function git_commit_id_describe_short () {
  COMMIT_ID=$(git describe --always --dirty 2>/dev/null)
  echo $COMMIT_ID
}

function git_commit_message () {
  COMMIT_MESSAGE=$(git log -1 --pretty=%B)
  echo $COMMIT_MESSAGE
}

function git_commit_message_short () {
  COMMIT_MESSAGE=$(git log -1 --pretty=%s)
  echo $COMMIT_MESSAGE
}

function git_commit_time () {
  COMMIT_DATE=$(git log -1 --pretty=%cd --date=iso-strict)
  echo $COMMIT_DATE
}

function git_commit_user_email () {
  COMMIT_USER_EMAIL=$(git log -1 --pretty=%ae)
  echo $COMMIT_USER_EMAIL
}

function git_commit_user_name () {
  COMMIT_USER_NAME=$(git log -1 --pretty=%an)
  echo $COMMIT_USER_NAME
}

function git_is_dirty () {
  if [[ -n $(git status --porcelain) ]]; then
    echo "true"
  else
    echo "false"
  fi
}

function git_remote_url () {
  REMOTE_URL=$(git config --get remote.origin.url)
  echo $REMOTE_URL
}

function git_local_branch_ahead_of_remote () {
  UPSTREAM=${1:-'@{u}'}
  LOCAL=$(git rev-parse @)
  REMOTE=$(git rev-parse "$UPSTREAM")
  BASE=$(git merge-base @ "$UPSTREAM")

  if [ $LOCAL = $REMOTE ]; then
    echo "false"
  elif [ $LOCAL = $BASE ]; then
    echo "false"
  elif [ $REMOTE = $BASE ]; then
    echo "true"
  else
    echo "false"
  fi
}

function git_local_branch_behind_remote () {
  UPSTREAM=${1:-'@{u}'}
  LOCAL=$(git rev-parse @)
  REMOTE=$(git rev-parse "$UPSTREAM")
  BASE=$(git merge-base @ "$UPSTREAM")

  if [ $LOCAL = $REMOTE ]; then
    echo "false"
  elif [ $LOCAL = $BASE ]; then
    echo "true"
  elif [ $REMOTE = $BASE ]; then
    echo "false"
  else
    echo "false"
  fi
}

function git_closest_tag_name () {
  TAG=$(git describe --tags --abbrev=0 2>/dev/null)
  echo $TAG
}

function git_closest_tag_commit_count () {
  COUNT=$(git rev-list --count $(git describe --tags --abbrev=0)..HEAD)
  echo $COUNT
}

function git_total_commit_count () {
  COUNT=$(git rev-list --count HEAD)
  echo $COUNT
}