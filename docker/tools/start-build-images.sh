#!/usr/bin/env sh
cd docker
sudo cp ../../${jobName}/sharding-distribution/sharding-proxy-distribution/target/*.tar.gz  ./sharding-proxy-bin.tar.gz
sudo docker-compose up -d
cd ..