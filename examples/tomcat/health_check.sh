#! /bin/bash

 url="$1"
port="$2"
host="$3"

test -z "$port" && port=80
test -z "$host" && host="localhost"

http_code=`curl --connect-timeout 5 $host:$port$url -w %{http_code} -sq -o /dev/null`

if [ $http_code -ge 200 ] && [ $http_code -le 399 ]
then
    exit 0
else
    exit 1
fi
