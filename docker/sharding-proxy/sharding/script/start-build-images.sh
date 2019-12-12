#!/usr/bin/env sh
cd docker
sudo cp ../../$1/sharding-distribution/sharding-proxy-distribution/target/*.tar.gz  ./sharding-proxy-bin.tar.gz
cd sharding-proxy/sharding
sudo docker-compose up -d
cd ../../../
pwd