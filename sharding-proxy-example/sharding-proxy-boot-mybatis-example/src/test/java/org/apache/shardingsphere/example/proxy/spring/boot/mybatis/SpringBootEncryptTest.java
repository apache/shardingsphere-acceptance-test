package org.apache.shardingsphere.example.proxy.spring.boot.mybatis;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.mybatis.service.SpringPojoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestMain.class)
@ActiveProfiles("encrypt")
public class SpringBootEncryptTest {
    
    @Autowired
    private SpringPojoService commonService;
    @Autowired
    @Qualifier("encrypt")
    private ExampleService encrypt;
    
    private static void process(final ConfigurableApplicationContext applicationContext) throws SQLException {
        ExampleService exampleService = getExampleService(applicationContext);
        exampleService.initEnvironment();
        exampleService.processSuccess();
        try {
            exampleService.processFailure();
        } catch (final Exception ex) {
            System.out.println(ex.getMessage());
            exampleService.printData();
        } finally {
            exampleService.cleanEnvironment();
        }
    }
    
    private static ExampleService getExampleService(final ConfigurableApplicationContext applicationContext) {
        return applicationContext.getBean("encrypt",ExampleService.class);
    }
    
    @Test
    public void assertCommonService() throws SQLException {
//        ExampleExecuteTemplate.run(encrypt);
//        AnnotationCommonServiceScenario.process1(commonService);
//        SpringResultAssertUtils.assertEncryptResult(commonService);
//        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTestMain.class)) {
            ExampleExecuteTemplate.run(encrypt);
//        }
    }
}
