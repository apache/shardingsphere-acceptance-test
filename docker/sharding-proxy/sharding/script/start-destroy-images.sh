#!/usr/bin/env sh
cd docker
sudo rm -f sharding-proxy-bin.tar.gz
cd sharding-proxy/sharding
#sudo docker-compose down --rmi all
sudo docker-compose down
cd ../../../