package org.apache.shardingsphere.example.encrypt.table.raw.jdbc;


import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.jdbc.common.SpringBootRawJdbcAssertUtils;
import org.apache.shardingsphere.example.core.jdbc.repository.UserRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.service.UserServiceImpl;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author linzesi
 */
public class ShardingEncryptTest {
    
    private static File getFile() {
        return new File(Thread.currentThread().getClass().getResource("/META-INF/sharding-encrypt.yaml").getFile());
    }
    
    private static ExampleService getExampleService(final DataSource dataSource) {
        return new UserServiceImpl(new UserRepositoryImpl(dataSource));
    }
    
    @Test
    public void assertEncryptDatabases() throws SQLException, IOException {
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(getFile());
        ExampleService exampleService = getExampleService(dataSource);
        ExampleExecuteTemplate.run(exampleService);
        SpringBootRawJdbcAssertUtils.assertEncrypt(exampleService);
    }
}
