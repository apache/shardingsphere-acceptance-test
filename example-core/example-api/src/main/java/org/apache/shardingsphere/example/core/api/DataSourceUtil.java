/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.example.core.api;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlEncryptDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlMasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class DataSourceUtil {
    
    private static final String HOST = "localhost";
    
    private static final int PORT = 3306;
    
    private static  final String LOCALHOST_PORT = "localhost:3307";
    
    private static final String USER_NAME = "root";
    
    private static final String PASSWORD = "";
    
    public static DataSource createDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", HOST, PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }
    
    public static DataSource createDataSourceWithShardingProxy(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setJdbcUrl(String.format("jdbc:mysql://%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", LOCALHOST_PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }

    public static DataSource createDataSourceWithYamlConfig(final String yamlFilePath, Class c, SceneType sceneType) throws IOException, SQLException {
        switch (sceneType){
            case ENCRYPT_ONLY:
                return YamlEncryptDataSourceFactory.createDataSource(new File(c.getResource(yamlFilePath).getFile()));
            case MASTER_SLAVE_ONLY:
                return YamlMasterSlaveDataSourceFactory.createDataSource(new File(c.getResource(yamlFilePath).getFile()));
            case SHARDING_ONLY:
            case SHARDING_ENCRYPT:
            case SHARDING_MASTER_SLAVE:
            case SHARDING_MASTER_SLAVE_ENCRYPT:
                return YamlShardingDataSourceFactory.createDataSource(new File(c.getResource(yamlFilePath).getFile()));
//            case ORCHESTRATION_ENCRYPT_ONLY:
//                return YamlOrchestrationEncryptDataSourceFactory.createDataSource(new File(c.getResource(yamlFilePath).getFile()));
//            case ORCHESTRATION_MASTER_SLAVE_ONLY:
//                return YamlOrchestrationMasterSlaveDataSourceFactory.createDataSource(new File(c.getResource(yamlFilePath).getFile()));
//            case ORCHESTRATION_SHARDING_ONLY:
//            case ORCHESTRATION_SHARDING_ENCRYPT:
//            case ORCHESTRATION_SHARDING_MASTER_SLAVE:
//            case ORCHESTRATION_SHARDING_MASTER_SLAVE_ENCRYPT:
//                return YamlOrchestrationShardingDataSourceFactory.createDataSource(new File(c.getResource(yamlFilePath).getFile()));
        }
        return null;
    }
    
}
