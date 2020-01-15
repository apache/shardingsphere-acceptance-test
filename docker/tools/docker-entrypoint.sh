#!/usr/bin/env bash
bash /usr/local/bin/wait-for-it.sh sharding-proxy-mysql:3306 -- sh /opt/sharding-proxy/bin/start.sh 3307 && tail -f /opt/sharding-proxy/logs/stdout.log