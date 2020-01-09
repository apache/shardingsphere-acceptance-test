package org.apache.shardingsphere.example.config;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptorRuleConfiguration;
import org.apache.shardingsphere.example.algorithm.PreciseModuloShardingTableAlgorithm;
import org.apache.shardingsphere.example.core.api.DataSourceUtil;
import org.apache.shardingsphere.example.core.api.DatabaseType;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShardingEncryptConfiguration implements ExampleConfiguration{

    @Override
    public DataSource getDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getTableRuleConfiguration());
        shardingRuleConfig.setEncryptRuleConfig(getEncryptRuleConfiguration());
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    private static TableRuleConfiguration getTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_user", "demo_ds_${0..1}.t_user_${[0, 1]}");
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "demo_ds_${user_id % 2}"));
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id","t_user_${user_id % 2}"));
        return result;
    }

    private EncryptRuleConfiguration getEncryptRuleConfiguration() {
        Properties props = new Properties();
        props.setProperty("aes.key.value", "123456");
        EncryptorRuleConfiguration encryptorAES = new EncryptorRuleConfiguration("AES", props);
        EncryptorRuleConfiguration encryptorMD5 = new EncryptorRuleConfiguration("MD5",new Properties());
        Map<String,EncryptColumnRuleConfiguration> columns = new HashMap<>();
        EncryptColumnRuleConfiguration columnUserName = new EncryptColumnRuleConfiguration("user_name", "user_name_cipher", "", "aes");
        EncryptColumnRuleConfiguration columnPwd = new EncryptColumnRuleConfiguration("pwd_plain", "pwd_cipher", "", "md5");
        columns.put("user_name",columnUserName);
        columns.put("pwd",columnPwd);
        EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(columns);
        EncryptRuleConfiguration result = new EncryptRuleConfiguration();
        result.getEncryptors().put("aes", encryptorAES);
        result.getEncryptors().put("md5",encryptorMD5);
        result.getTables().put("t_user", tableConfig);
        return result;
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("demo_ds_0", DataSourceUtil.createDataSource("demo_ds_0", DatabaseType.MYSQL));
        result.put("demo_ds_1", DataSourceUtil.createDataSource("demo_ds_1", DatabaseType.MYSQL));
        return result;
    }

    private static Properties getProperties() {
        Properties result = new Properties();
        result.setProperty("worker.id", "123");
        return result;
    }
}
