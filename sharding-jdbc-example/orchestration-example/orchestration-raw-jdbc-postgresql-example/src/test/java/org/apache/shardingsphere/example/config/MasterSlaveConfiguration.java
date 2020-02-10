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

package org.apache.shardingsphere.example.config;

import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.example.core.api.DataSourceUtil;
import org.apache.shardingsphere.example.core.api.DatabaseType;
import org.apache.shardingsphere.orchestration.config.OrchestrationConfiguration;
import org.apache.shardingsphere.orchestration.reg.api.RegistryCenterConfiguration;
import org.apache.shardingsphere.shardingjdbc.orchestration.api.OrchestrationMasterSlaveDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class MasterSlaveConfiguration implements ExampleConfiguration{

    @Override
    public DataSource getDataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("demo_ds_master_slave", "demo_ds_master", Arrays.asList("demo_ds_slave_0", "demo_ds_slave_1"));
        return OrchestrationMasterSlaveDataSourceFactory.createDataSource(
                createDataSourceMap(),
                masterSlaveRuleConfig,
                new Properties(),
                new OrchestrationConfiguration("orchestration-postgresql-master-slave",getRegistryCenterConfiguration(),true));
    }
    
    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("demo_ds_master", DataSourceUtil.createDataSource("demo_ds_master", DatabaseType.POSTGRESQL));
        result.put("demo_ds_slave_0", DataSourceUtil.createDataSource("demo_ds_slave_0", DatabaseType.POSTGRESQL));
        result.put("demo_ds_slave_1", DataSourceUtil.createDataSource("demo_ds_slave_1", DatabaseType.POSTGRESQL));
        return result;
    }
    private RegistryCenterConfiguration getRegistryCenterConfiguration() {
        RegistryCenterConfiguration regConfig = new RegistryCenterConfiguration("zookeeper");
        regConfig.setServerLists("localhost:2181");
        regConfig.setNamespace("orchestration-raw-jdbc-postgresql");
        return regConfig;
    }
}
