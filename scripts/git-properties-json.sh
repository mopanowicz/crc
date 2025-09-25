#!/usr/bin/env bash

OUTPUT_FILE=${1:-git-properties.json}

source $(dirname $0)/git-library.sh

jq_command='.git.branch=$git_branch'
jq_command+='|.git.build.hostname=$git_build_hostname'
jq_command+='|.git.build.time=$git_build_time'
jq_command+='|.git.build.user.name=$git_build_user_name'
jq_command+='|.git.build.user.email=$git_build_user_email'
jq_command+='|.git.build.version=$git_build_version'
jq_command+='|.git.closest.tag.commit.count=$git_closest_tag_commit_count'
jq_command+='|.git.closest.tag.name=$git_closest_tag_name'
jq_command+='|.git.commit.author.time=$git_commit_author_time'
jq_command+='|.git.commit.committer.time=$git_commit_commiter_time'
jq_command+='|.git.commit.id.full=$git_commit_id'
jq_command+='|.git.commit.id.abbrev=$git_commit_id_short'
jq_command+='|.git.commit.id.describe=$git_commit_id_describe'
jq_command+='|.git.commit.id."describe-short"=$git_commit_id_describe_short'
jq_command+='|.git.commit.message.full=$git_commit_message'
jq_command+='|.git.commit.message.short=$git_commit_message_short'
jq_command+='|.git.commit.time=$git_commit_commiter_time'
jq_command+='|.git.commit.user.email=$git_commit_user_email'
jq_command+='|.git.commit.user.name=$git_commit_user_name'
jq_command+='|.git.dirty=$git_is_dirty'
jq_command+='|.git.local.branch.ahead=$git_local_branch_ahead_of_remote'
jq_command+='|.git.local.branch.behind=$git_local_branch_behind_remote'
jq_command+='|.git.remote.origin.url=$git_remote_url'
jq_command+='|.git.tag=$git_closest_tag_name'
jq_command+='|.git.tags=$git_closest_tag_name'
jq_command+='|.git.total.commit.count=$git_total_commit_count'

cat $(dirname $0)/git-properties-template.json | jq $jq_command \
  --arg git_branch "$(git_branch main)" \
  --arg git_build_hostname "$(git_build_hostname)" \
  --arg git_build_time "$(git_build_time)" \
  --arg git_build_user_name "$(git_build_user_name)" \
  --arg git_build_user_email "$(git_build_user_email)" \
  --arg git_build_version "$(git_build_version)" \
  --arg git_closest_tag_commit_count "$(git_closest_tag_commit_count)" \
  --arg git_closest_tag_name "$(git_closest_tag_name)" \
  --arg git_commit_author_time "$(git_commit_author_time)" \
  --arg git_commit_commiter_time "$(git_commit_commiter_time)" \
  --arg git_commit_id "$(git_commit_id)" \
  --arg git_commit_id_short "$(git_commit_id_short)" \
  --arg git_commit_id_describe "$(git_commit_id_describe)" \
  --arg git_commit_id_describe_short "$(git_commit_id_describe_short)" \
  --arg git_commit_message "$(git_commit_message)" \
  --arg git_commit_message_short "$(git_commit_message_short)" \
  --arg git_commit_user_email "$(git_commit_user_email)" \
  --arg git_commit_user_name "$(git_commit_user_name)" \
  --arg git_is_dirty "$(git_is_dirty)" \
  --arg git_local_branch_ahead_of_remote "$(git_local_branch_ahead_of_remote)" \
  --arg git_local_branch_behind_remote "$(git_local_branch_behind_remote)" \
  --arg git_remote_url "$(git_remote_url)" \
  --arg git_total_commit_count "$(git_total_commit_count)" \
  > $OUTPUT_FILE
