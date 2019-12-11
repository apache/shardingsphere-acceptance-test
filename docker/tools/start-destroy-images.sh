#!/usr/bin/env sh
cd docker
sudo docker-compose down --rmi all
sudo rm -f sharding-proxy-bin.tar.gz
#sudo docker-compose down
cd ..