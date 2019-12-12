#!/usr/bin/env sh
cd docker
sudo rm -f sharding-proxy-bin.tar.gz
cd sharding-proxy/sharding
#sudo docker-compose down --rmi all remove all images of service
sudo docker-compose down
cd ../../../