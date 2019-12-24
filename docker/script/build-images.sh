#!/usr/bin/env sh
PORT_JDBC_MYSQL=3310
PORT_JDBC_ZK=2188
PORT_PROXY_SHARDING=3307
PORT_PROXY_ORCH=3307
PORT_PROXY_ZK=2185

cd docker
ip=`ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:" | head -1`
sudo cp ../../$1/sharding-distribution/sharding-proxy-distribution/target/*.tar.gz  ./sharding-proxy-bin.tar.gz
cd sharding-jdbc/jdbc
sudo docker-compose up -d
cd ../../
cd sharding-proxy/proxy
sudo docker-compose up -d
cd ../../../
sudo sed -i "s/localhost:3306/${ip}:${PORT_JDBC_MYSQL}/g" `grep localhost:3306 -rl sharding-jdbc-example/`
sudo sed -i "s/localhost:2181/${ip}:${PORT_JDBC_ZK}/g" `grep localhost:2181 -rl sharding-jdbc-example/`
sudo sed -i "s/localhost:3307/${ip}:${PORT_PROXY_SHARDING}/g" `grep localhost:3307 -rl sharding-proxy-example/sharding-proxy-boot-mybatis-example/`
sudo sed -i "s/localhost:3307/${ip}:${PORT_PROXY_ORCH}/g" `grep localhost:3307 -rl sharding-proxy-example/sharding-proxy-orchestration-boot-example/`
sudo sed -i "s/localhost:2181/${ip}:${PORT_PROXY_ZK}/g" `grep localhost:2181 -rl sharding-proxy-example/`
sudo chmod 777 docker/tools/wait-for-it.sh
sudo bash docker/tools/wait-for-it.sh ${ip}:${PORT_PROXY_SHARDING} -- echo "sharding-proxy is available!"
sudo bash docker/tools/wait-for-it.sh ${ip}:${PORT_PROXY_ORCH} -- echo "orchestration-proxy is available!"
