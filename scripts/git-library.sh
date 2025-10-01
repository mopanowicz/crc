#!/usr/bin/env bash

all_properties=(
  "git.branch"
  "git.build.hostname"
  "git.build.time"
  "git.build.user.name"
  "git.build.user.email"
  "git.build.version"
  "git.closest.tag.commit.count"
  "git.closest.tag.name"
  "git.commit.author.time"
  "git.commit.committer.time"
  "git.commit.id.full"
  "git.commit.id.abbrev"
  "git.commit.id.describe"
  "git.commit.id.describe-short"
  "git.commit.message.full"
  "git.commit.message.short"
  "git.commit.time"
  "git.commit.user.email"
  "git.commit.user.name"
  "git.dirty"
  "git.local.branch.ahead"
  "git.local.branch.behind"
  "git.remote.origin.url"
  "git.tag"
  "git.tags"
  "git.total.commit.count"
)

function is_property_listed () {
  local property=$1
  for i in $(cat git-properties.list); do
    if [[ "$i" == "$property" ]]; then
      return 0
    fi
  done
  return 1
}

function get_property_function_name () {
  local property=$1
  echo "${property//./_}"
}

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
    BRANCH=$commit
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
  COMMIT_DATE=$(git log -1 --pretty=%aI)
  echo $COMMIT_DATE
}

function git_commit_committer_time () {
  COMMIT_DATE=$(git log -1 --pretty=%cI)
  echo $COMMIT_DATE
}

function git_commit_time () {
  git_commit_committer_time
}

function git_commit_id_full () {
  COMMIT_ID=$(git rev-parse HEAD)
  echo $COMMIT_ID
}

function git_commit_id_abbrev () {
  COMMIT_ID=$(git rev-parse --short HEAD)
  echo $COMMIT_ID
}

function git_commit_id_describe () {
  COMMIT_ID=$(git describe --always --long --dirty 2>/dev/null)
  echo $COMMIT_ID
}

function git_commit_id_describe-short () {
  COMMIT_ID=$(git describe --always --dirty 2>/dev/null)
  echo $COMMIT_ID
}

function git_commit_message_full () {
  COMMIT_MESSAGE=$(git log -1 --pretty=%B)
  echo $COMMIT_MESSAGE
}

function git_commit_message_short () {
  COMMIT_MESSAGE=$(git log -1 --pretty=%s)
  echo $COMMIT_MESSAGE
}

function git_commit_user_email () {
  COMMIT_USER_EMAIL=$(git log -1 --pretty=%ae)
  echo $COMMIT_USER_EMAIL
}

function git_commit_user_name () {
  COMMIT_USER_NAME=$(git log -1 --pretty=%an)
  echo $COMMIT_USER_NAME
}

function git_dirty () {
  if [[ -n $(git status --porcelain) ]]; then
    echo "true"
  else
    echo "false"
  fi
}

function git_remote_origin_url () {
  REMOTE_URL=$(git config --get remote.origin.url)
  echo $REMOTE_URL
}

function git_local_branch_ahead () {
  UPSTREAM=${1:-'@{u}'}
  LOCAL=$(git rev-parse @)
  REMOTE=$(git rev-parse "$UPSTREAM")
  echo $(git rev-list --count $REMOTE..$LOCAL)
}

function git_local_branch_behind () {
  UPSTREAM=${1:-'@{u}'}
  LOCAL=$(git rev-parse @)
  REMOTE=$(git rev-parse "$UPSTREAM")
  echo $(git rev-list --count $LOCAL..$REMOTE)
}

function git_tag () {
  TAG=$(git describe --tags --abbrev=0 2>/dev/null)
  echo $TAG
}

function git_tags () {
  TAGS=$(git tag --points-at HEAD)
  echo $TAGS
}

function git_closest_tag_commit_count () {
  COUNT=$(git rev-list --count $(git describe --tags --abbrev=0)..HEAD)
  echo $COUNT
}

function git_total_commit_count () {
  COUNT=$(git rev-list --count HEAD)
  echo $COUNT
}
