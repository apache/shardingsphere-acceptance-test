package org.apache.shardingsphere.example.orchestration.spring.boot;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

/**
 * @author wanghaitao
 * @date 2019/12/13 16:33
 */
public class ZookeeperShardingTableDatabasesTest {
      public void assertShardingTableDatabasesWithRegistryCenter() throws SQLException {
          try (ConfigurableApplicationContext applicationContext = SpringApplication.run(ExampleMain.class, new String[1])) {
              ExampleService exampleService = applicationContext.getBean(ExampleService.class);
              ExampleExecuteTemplate.run(exampleService);

          }
      }
}
