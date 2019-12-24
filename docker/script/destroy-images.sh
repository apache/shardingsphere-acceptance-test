#!/usr/bin/env sh
pwd
cd docker
sudo rm -f sharding-proxy-bin.tar.gz
cd sharding-jdbc/jdbc
#sudo docker-compose down --rmi all remove all images of service
sudo docker-compose down
cd ../../
cd sharding-proxy/proxy/compose-sharding
#sudo docker-compose down --rmi all remove all images of service
sudo docker-compose down
cd ../compose-orch
cd sharding-proxy/proxy/compose-sharding
cd ../../../../