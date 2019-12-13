#!/usr/bin/env sh
cd docker
ip=`ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:" | head -1`
sudo cp ../../$1/sharding-distribution/sharding-proxy-distribution/target/*.tar.gz  ./sharding-proxy-bin.tar.gz
cd sharding-proxy/sharding
sudo docker-compose up -d
cd ../../../
sudo sed -i "s/localhost:3306/${ip}:3309/g" `grep localhost:3306 -rl sharding-jdbc-example/`
sudo sed -i "s/localhost:3307/${ip}:3308/g" `grep localhost:3307 -rl sharding-proxy-example/`
sudo chmod 777 docker/tools/wait-for-it.sh
sudo bash docker/tools/wait-for-it.sh ${ip}:3308 -- echo "sharding-proxy is available!"