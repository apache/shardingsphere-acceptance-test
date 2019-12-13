package org.apache.shardingsphere.example.orchestration.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wanghaitao
 * @date 2019/12/13 17:45
 */
@ComponentScan("org.apache.shardingsphere.example.core.jpa")
@EntityScan(basePackages = "org.apache.shardingsphere.example.core.jpa.entity")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class SpringBootTestWithRegistryCenterMain {
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootTestWithRegistryCenterMain.class, args);
    }
}
