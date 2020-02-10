package org.apache.shardingsphere.example;

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
@ActiveProfiles("encrypt")
public class SpringBootEncryptTest {
    
    @Resource(name = "encrypt")
    ExampleService exampleService;
    
    @Test
    public void commonService() throws SQLException {
        ExampleExecuteTemplate.run(exampleService);
        SpringResultAssertUtils.assertExampleServiceEncryptResult(exampleService);
    }
}
