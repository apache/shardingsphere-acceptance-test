package org.apache.shardingsphere.example;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.jdbc.common.RawJdbcAssertUtils;
import org.apache.shardingsphere.example.core.jdbc.repository.postgresql.*;
import org.apache.shardingsphere.example.core.jdbc.service.UserServiceImpl;
import org.apache.shardingsphere.example.factory.YamlDataSourceFactory;
import org.apache.shardingsphere.example.type.ShardingType;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class RawJDBCYamlShardingMasterSlaveEncryptTest {
    @Test
    public void assertCommonService() throws SQLException, IOException {
        DataSource dataSource = YamlDataSourceFactory.newInstance(ShardingType.SHARDING_MASTER_SLAVE_ENCRYPT);
        ExampleService exampleService = new UserServiceImpl(new UserRepositoryImpl(dataSource));
        ExampleExecuteTemplate.run(exampleService);
        RawJdbcAssertUtils.assertShardingMasterSlaveEncrypt(exampleService);
    }
}
