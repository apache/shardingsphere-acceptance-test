package org.apache.shardingsphere.example.config;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.example.algorithm.PreciseModuloShardingTableAlgorithm;
import org.apache.shardingsphere.example.core.api.DataSourceUtil;
import org.apache.shardingsphere.example.core.api.DatabaseType;
import org.apache.shardingsphere.orchestration.config.OrchestrationConfiguration;
import org.apache.shardingsphere.orchestration.reg.api.RegistryCenterConfiguration;
import org.apache.shardingsphere.shardingjdbc.orchestration.api.OrchestrationShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ShardingConfiguration implements ExampleConfiguration{

    @Override
    public DataSource getDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        shardingRuleConfig.getBroadcastTables().add("t_address");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "demo_ds_${user_id % 2}"));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new PreciseModuloShardingTableAlgorithm()));
        return OrchestrationShardingDataSourceFactory.createDataSource(
                createDataSourceMap()
                , shardingRuleConfig
                , new Properties()
                ,new OrchestrationConfiguration("orchestration-postgresql-sharding",getRegistryCenterConfiguration(),true));
    }

    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_order", "demo_ds_${0..1}.t_order_${0..1}");
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "demo_ds_${user_id % 2}"));
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id","t_order_${order_id % 2}"));
        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_id", getProperties()));
        return result;
    }

    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_order_item", "demo_ds_${0..1}.t_order_item_${0..1}");
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "demo_ds_${user_id % 2}"));
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id","t_order_item_${order_id % 2}"));
        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_item_id", getProperties()));
        return result;
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("demo_ds_0", DataSourceUtil.createDataSource("demo_ds_0", DatabaseType.POSTGRESQL));
        result.put("demo_ds_1", DataSourceUtil.createDataSource("demo_ds_1", DatabaseType.POSTGRESQL));
        return result;
    }

    private RegistryCenterConfiguration getRegistryCenterConfiguration() {
        RegistryCenterConfiguration regConfig = new RegistryCenterConfiguration("zookeeper");
        regConfig.setServerLists("localhost:2181");
        regConfig.setNamespace("orchestration-raw-jdbc-postgresql");
        return regConfig;
    }

    private static Properties getProperties() {
        Properties result = new Properties();
        result.setProperty("worker.id", "123");
        return result;
    }
}
