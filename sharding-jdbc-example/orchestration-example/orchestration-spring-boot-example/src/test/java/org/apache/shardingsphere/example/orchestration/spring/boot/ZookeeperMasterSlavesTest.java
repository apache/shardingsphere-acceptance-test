package org.apache.shardingsphere.example.orchestration.spring.boot;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.jpa.common.SpringBootJpaAssertUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * @author wanghaitao
 * @date 2019/12/13 18:40
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestWithRegistryCenterMain.class)
@ActiveProfiles("local-zookeeper-master-slave")
public class ZookeeperMasterSlavesTest {

    @Test
    public void assertExampleServiceWithRegistryCenter() throws SQLException {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTestWithRegistryCenterMain.class)) {
            ExampleService exampleService = applicationContext.getBean(ExampleService.class);
            ExampleExecuteTemplate.run(exampleService);
            SpringBootJpaAssertUtils.assertMasterSlaves(exampleService);
        }
    }
}
