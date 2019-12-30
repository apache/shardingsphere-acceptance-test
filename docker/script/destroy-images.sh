#!/usr/bin/env sh
cd docker
sudo rm -f sharding-proxy-bin.tar.gz
cd sharding-jdbc/jdbc
#sudo docker-compose down --rmi all //remove all images of service
if [[ $bool == true ]] ; then
  sudo docker-compose down --rmi all
elif [[ $bool == false || $bool == "" ]] ; then
  sudo docker-compose down
fi
cd ../../
cd sharding-proxy/proxy
#sudo docker-compose down --rmi all //remove all images of service
if [[ $bool == true ]] ; then
  sudo docker-compose down --rmi all
elif [[ $bool == false || $bool == "" ]] ; then
  sudo docker-compose down
fi
cd ../../../