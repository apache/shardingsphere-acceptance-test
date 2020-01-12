#!/usr/bin/env sh
PORT_JDBC_MYSQL=3310
PORT_JDBC_ZK=2188
PORT_JDBC_PG=5433

PORT_PROXY_MYSQL_SHARDING=3308
PORT_PROXY_MYSQL_ORCH=3307
PORT_PROXY_MYSQL_ZK=2185

PORT_PROXY_PG_SHARDING=13308
PORT_PROXY_PG_ORCH=13307
PORT_PROXY_PG_ZK=12185

cd docker
ip=`ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:" | head -1`
sudo cp ../../$1/sharding-distribution/sharding-proxy-distribution/target/*.tar.gz  ./sharding-proxy-bin.tar.gz
cd sharding-jdbc/jdbc
sudo docker-compose up -d
cd ../../
cd sharding-proxy/proxy-mysql
sudo docker-compose up -d
cd ../proxy-postgresql
sudo docker-compose up -d
cd ../../../

sudo sed -i "s/localhost/${ip}/g" `grep localhost -rl example-core/example-api/`
sudo sed -i "s/3306/${PORT_JDBC_MYSQL}/g" `grep 3306 -rl example-core/example-api/`
sudo sed -i "s/5432/${PORT_JDBC_PG}/g" `grep 5432 -rl example-core/example-api/`
sudo sed -i "s/PROXY_MYSQL_PORT = 3307/PROXY_MYSQL_PORT = ${PORT_PROXY_MYSQL_SHARDING}/g" `grep PROXY_MYSQL_PORT = 3307 -rl example-core/example-api/`
sudo sed -i "s/PROXY_POSTGRESQL_PORT = 3307/PROXY_POSTGRESQL_PORT = ${PORT_PROXY_PG_SHARDING}/g" `grep PROXY_POSTGRESQL_PORT = 3307 -rl example-core/example-api/`

sudo sed -i "s/localhost:3306/${ip}:${PORT_JDBC_MYSQL}/g" `grep localhost:3306 -rl sharding-jdbc-example/`
sudo sed -i "s/localhost:5432/${ip}:${PORT_JDBC_PG}/g" `grep localhost:5432 -rl sharding-jdbc-example/`
sudo sed -i "s/localhost:2181/${ip}:${PORT_JDBC_ZK}/g" `grep localhost:2181 -rl sharding-jdbc-example/`

sudo sed -i "s/localhost:3307/${ip}:${PORT_PROXY_MYSQL_SHARDING}/g" `grep localhost:3307 -rl sharding-proxy-example/sharding-proxy-boot-mybatis-mysql-example/`
sudo sed -i "s/localhost:3307/${ip}:${PORT_PROXY_MYSQL_ORCH}/g" `grep localhost:3307 -rl sharding-proxy-example/sharding-proxy-orchestration-boot-mysql-example/`
sudo sed -i "s/localhost:2181/${ip}:${PORT_PROXY_MYSQL_ZK}/g" `grep localhost:2181 -rl sharding-proxy-example/sharding-proxy-orchestration-boot-mysql-example/`

sudo sed -i "s/localhost:3307/${ip}:${PORT_PROXY_PG_SHARDING}/g" `grep localhost:3307 -rl sharding-proxy-example/sharding-proxy-boot-mybatis-postgresql-example/`
sudo sed -i "s/localhost:3307/${ip}:${PORT_PROXY_PG_ORCH}/g" `grep localhost:3307 -rl sharding-proxy-example/sharding-proxy-orchestration-boot-postgresql-example/`
sudo sed -i "s/localhost:2181/${ip}:${PORT_PROXY_PG_ZK}/g" `grep localhost:2181 -rl sharding-proxy-example/sharding-proxy-orchestration-boot-postgresql-example/`

sudo chmod u+x docker/tools/wait-for-it.sh
sudo bash docker/tools/wait-for-it.sh ${ip}:${PORT_PROXY_MYSQL_SHARDING} -- echo "sharding-proxy-mysql"
sudo bash docker/tools/wait-for-it.sh ${ip}:${PORT_PROXY_MYSQL_ORCH} -- echo "orchestration-proxy-mysql"
sudo bash docker/tools/wait-for-it.sh ${ip}:${PORT_PROXY_PG_SHARDING} -- echo "sharding-proxy-postgresql"
sudo bash docker/tools/wait-for-it.sh ${ip}:${PORT_PROXY_PG_ORCH} -- echo "orchestration-proxy-postgresql"
