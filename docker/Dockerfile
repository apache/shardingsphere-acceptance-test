#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


FROM apache/sharding-proxy:latest
MAINTAINER ShardingSphere "dev@shardingsphere.incubator.apache.org"

ENV LOCAL_PATH /opt/sharding-proxy

COPY ./tools/wait-for-it.sh /usr/local/bin/
COPY ./tools/docker-entrypoint.sh /usr/local/bin/
RUN chmod 777 /usr/local/bin/wait-for-it.sh
RUN chmod 777 /usr/local/bin/docker-entrypoint.sh
COPY ./driver/mysql-connector-java-5.1.47.jar ${LOCAL_PATH}/lib/mysql-connector-java-5.1.47.jar
#/usr/local/bin/wait-for-it.sh sharding-mysql:3306 --
ENTRYPOINT /sharding-proxy/bin/start.sh $PORT && tail -f /sharding-proxy/logs/stdout.log
