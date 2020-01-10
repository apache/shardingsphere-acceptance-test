package org.apache.shardingsphere.example;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.jdbc.common.RawJdbcAssertUtils;
import org.apache.shardingsphere.example.core.jdbc.repository.UserRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.service.UserServiceImpl;
import org.apache.shardingsphere.example.factory.DataSourceFactory;
import org.apache.shardingsphere.example.type.ShardingType;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class RawJDBCJavaEncryptTest {
    @Test
    public void assertCommonService() throws SQLException {
        DataSource dataSource = DataSourceFactory.newInstance(ShardingType.ENCRYPT);
        ExampleService exampleService = new UserServiceImpl(new UserRepositoryImpl(dataSource));
        ExampleExecuteTemplate.run(exampleService);
        RawJdbcAssertUtils.assertEncrypt(exampleService);
    }
}
