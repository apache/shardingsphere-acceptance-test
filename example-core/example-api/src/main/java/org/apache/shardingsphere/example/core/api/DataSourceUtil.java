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

import javax.sql.DataSource;

public final class DataSourceUtil {
    
    private static final String HOST = "localhost";
    
    private static final int MYSQL_PORT = 3306;
    
    private static final int PROXY_MYSQL_PORT = 3307;
    
    private static final int PROXY_POSTGRESQL_PORT = 3307;
    
    private static final String MYSQL_USER_NAME = "root";
    
    private static final String MYSQL_PASSWORD = "";
    
    private static final int PG_PORT = 5432;
    
    private static final String PG_USER_NAME = "postgres";
    
    private static final String PG_PASSWORD = "";
    
    public static DataSource createDataSource(final String dataSourceName, DatabaseType dbType) {
        switch (dbType) {
            case MYSQL:
                return createMySQLDataSource(dataSourceName);
            case POSTGRESQL:
                return createPostgreSQLDataSource(dataSourceName);
            case PROXY_MYSQL:
                return createProxyMySQLDataSource(dataSourceName);
            case PROXY_POSTGRESQL:
                return createProxyPostgreSQLDataSource(dataSourceName);
            default:
                throw new UnsupportedOperationException(dbType.name());
        }
    }
    
    private static DataSource createMySQLDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", HOST, MYSQL_PORT, dataSourceName));
        result.setUsername(MYSQL_USER_NAME);
        result.setPassword(MYSQL_PASSWORD);
        return result;
    }
    
    private static DataSource createPostgreSQLDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(org.postgresql.Driver.class.getName());
        result.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/shardingsphere?currentSchema=%s", HOST, PG_PORT, dataSourceName));
        result.setUsername(PG_USER_NAME);
        result.setPassword(PG_PASSWORD);
        return result;
    }
    
    
    private static DataSource createProxyMySQLDataSource(String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?useServerPrepStmts=true&cachePrepStmts=true", HOST, PROXY_MYSQL_PORT, dataSourceName));
        result.setUsername(MYSQL_USER_NAME);
        result.setPassword(MYSQL_PASSWORD);
        return result;
    }
    
    private static DataSource createProxyPostgreSQLDataSource(String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(org.postgresql.Driver.class.getName());
        result.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s?useServerPrepStmts=true&cachePrepStmts=true", HOST, PROXY_POSTGRESQL_PORT, dataSourceName));
        result.setUsername(MYSQL_USER_NAME);
        result.setPassword(MYSQL_PASSWORD);
        return result;
    }
}
