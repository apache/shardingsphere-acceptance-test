#!/usr/bin/env sh
cd docker
sudo rm -f sharding-proxy-bin.tar.gz
cd sharding-jdbc/jdbc
#sudo docker-compose down --rmi all //remove all images of service
if [[ $bool == true ]] ; then
  sudo docker-compose down -v --rmi all
elif [[ $bool == false || $bool == "" ]] ; then
  sudo docker-compose down -v
fi
cd ../../
cd sharding-proxy/proxy-mysql
#sudo docker-compose down --rmi all //remove all images of service
if [[ $bool == true ]] ; then
  sudo docker-compose down -v --rmi all
elif [[ $bool == false || $bool == "" ]] ; then
  sudo docker-compose down -v
fi
cd ../proxy-postgresql
if [[ $bool == true ]] ; then
  sudo docker-compose down -v --rmi all
elif [[ $bool == false || $bool == "" ]] ; then
  sudo docker-compose down -v
fi
cd ../../../