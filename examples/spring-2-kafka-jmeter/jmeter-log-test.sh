#!/bin/bash

httpsampler_protocol=$1
httpsampler_domain=$2
httpsampler_port=$3

output_dir_prefix=$4

threadgroup_num_threads=$5
loopcontroller_loops=$6

httprequest_sleepMillis=0
httprequest_messageLength=1000
httprequest_messageCount=10

function validate_parameter() {
	param_name=$1
	param_value=${!param_name}
	if [ "$param_value" == "" ]; then
		echo "$param_name is required"
		exit 1
	fi
}

validate_parameter "httpsampler_protocol"
validate_parameter "httpsampler_domain"
validate_parameter "httpsampler_port"
validate_parameter "output_dir_prefix"
validate_parameter "threadgroup_num_threads"
validate_parameter "loopcontroller_loops"

output_dir="output/${output_dir_prefix}-${threadgroup_num_threads}-${loopcontroller_loops}"
console_txt="${output_dir}/console.txt"
jmeter_report_dir="${output_dir}-jmeter"

mkdir -p $output_dir

echo "#"
echo "# url=${httpsampler_protocol}:${httpsampler_domain}:${httpsampler_port}"
echo "# jmeter_report_dir=${jmeter_report_dir}"
echo "# console_txt=${console_txt}"
echo "#"

jmeter -n -t log-test.jmx -l ${jmeter_report_dir}/result.out -e -f -o ${jmeter_report_dir} \
  -Jthreadgroup_num_threads="${threadgroup_num_threads}" \
  -Jloopcontroller_loops="${loopcontroller_loops}" \
  -Jhttpsampler_protocol="${httpsampler_protocol}" \
  -Jhttpsampler_domain="${httpsampler_domain}" \
  -Jhttpsampler_port="${httpsampler_port}" \
  -Jhttprequest_sleepMillis="${httprequest_sleepMillis}" \
  -Jhttprequest_messageLength="${httprequest_messageLength}" \
  -Jhttprequest_messageCount="${httprequest_messageCount}" \
  | tee ${console_txt}
