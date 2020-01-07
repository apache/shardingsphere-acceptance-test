package org.apache.shardingsphere.example.proxy.spring.boot.mybatis;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.mybatis.common.SpringResultAssertUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author linzesi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestMain.class)
@ActiveProfiles("sharding-ms-encrypt")
public class SpringBootOrchestrationShardingMasterSlaveEncryptTest {
    
    @Resource(name = "encrypt")
    ExampleService exampleService;
    
    @Test
    public void commonService() throws SQLException {
        ExampleExecuteTemplate.run(exampleService);
        SpringResultAssertUtils.assertExampleServiceMasterSlaveEncryptResult(exampleService);
    }
}
