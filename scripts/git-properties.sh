#!/usr/bin/env bash

FORMAT=${1:-properties}
if [[ "$FORMAT" != "properties" && "$FORMAT" != "json" ]]; then
  echo "Unsupported format: $FORMAT"
  echo "Supported formats: properties, json"
  exit 1
fi

OUTPUT_FILE=${2}
if [[ "$OUTPUT_FILE" == "" ]]; then
  if [[ "$FORMAT" == "properties" ]]; then
    OUTPUT_FILE="git.properties"
  else
    OUTPUT_FILE="git-properties.json"
  fi
fi

source $(dirname $0)/git-library.sh

if [[ "$FORMAT" == "json" ]]; then
  OUTPUT="{}"
  for property in "${all_properties[@]}"; do
    if is_property_listed "$property"; then
      function_name=$(get_property_function_name "$property")
      jq_command="setpath((\"$property\" | split(\".\")); \"$($function_name)\" )"
      OUTPUT=$(echo $OUTPUT | jq "$jq_command")
    fi
  done
  echo $OUTPUT | jq -r > $OUTPUT_FILE
fi

if [[ "$FORMAT" == "properties" ]]; then
  {
    for property in "${all_properties[@]}"; do
      if is_property_listed "$property"; then
        function_name=$(get_property_function_name "$property")
        echo "$property=$($function_name)"
      fi
    done
  } > $OUTPUT_FILE
fi
