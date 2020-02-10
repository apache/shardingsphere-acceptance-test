package org.apache.shardingsphere.example;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.mybatis.common.SpringResultAssertUtils;
import org.apache.shardingsphere.example.core.mybatis.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class SpringNamespaceEncryptTest {
    private static final String CONFIG_FILE = "META-INF/application-encrypt.xml";
    
    @Test
    public void assertCommonService() throws SQLException {
        try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONFIG_FILE)) {
            ExampleService exampleService = applicationContext.getBean(UserServiceImpl.class);
            ExampleExecuteTemplate.run(exampleService);
            SpringResultAssertUtils.assertExampleServiceEncryptResult(exampleService);
        }
    }
}
