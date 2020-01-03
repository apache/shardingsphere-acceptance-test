package org.apache.shardingsphere.example.orchestration.spring.boot;

import org.apache.shardingsphere.example.core.api.senario.AnnotationCommonServiceScenario;
import org.apache.shardingsphere.example.core.mybatis.common.SpringResultAssertUtils;
import org.apache.shardingsphere.example.core.mybatis.service.SpringPojoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * @author linzesi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWithRegistryCenterTestMain.class)
@ActiveProfiles("cloud-zookeeper-sharding-databases-tables")
public class CloudZookeeperShardingDatabasesTablesTest {
    
    @Autowired
    private SpringPojoService commonService;
    
    @Test
    public void assertCommonService() throws SQLException {
        AnnotationCommonServiceScenario scenario = new AnnotationCommonServiceScenario(commonService);
        scenario.process();
        SpringResultAssertUtils.assertShardingDatabaseAndTableResult(commonService);
    }
}
