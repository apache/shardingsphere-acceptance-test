package org.apache.shardingsphere.example;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.jdbc.common.RawJdbcAssertUtils;
import org.apache.shardingsphere.example.core.jdbc.repository.postgresql.AddressRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.repository.postgresql.OrderItemRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.repository.postgresql.OrderRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.service.OrderServiceImpl;
import org.apache.shardingsphere.example.factory.DataSourceFactory;
import org.apache.shardingsphere.example.type.ShardingType;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class RawJDBCJavaMasterSlaveTest {
    
    @Test
    public void assertCommonService() throws SQLException {
        DataSource dataSource = DataSourceFactory.newInstance(ShardingType.MASTER_SLAVE);
        ExampleService exampleService = new OrderServiceImpl(new OrderRepositoryImpl(dataSource), new OrderItemRepositoryImpl(dataSource), new AddressRepositoryImpl(dataSource));
        ExampleExecuteTemplate.run(exampleService);
        RawJdbcAssertUtils.assertMasterSlaves(exampleService);
    }
}
