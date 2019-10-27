#!/bin/bash

url="$1"
shift
cmd="$@"
status_code=$(curl --write-out %{http_code} --silent --output /dev/null ${url})
echo $status_code
while [[ "$status_code" -ne 200 ]]; do
  status_code=$(curl --write-out %{http_code} --silent --output /dev/null ${url})
  echo $status_code
  sleep 1
done
echo "execute cmd $cmd"
exec $cmd

