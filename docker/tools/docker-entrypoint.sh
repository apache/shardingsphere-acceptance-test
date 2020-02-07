#!/usr/bin/env bash
bash /usr/local/bin/wait-for-it.sh sharding-proxy-mysql:3306 -- sh /sharding-proxy/bin/start.sh 3307 && tail -f /sharding-proxy/logs/stdout.log