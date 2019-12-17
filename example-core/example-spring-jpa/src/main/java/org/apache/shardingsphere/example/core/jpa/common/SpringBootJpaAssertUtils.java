package org.apache.shardingsphere.example.core.jpa.common;

import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.api.trace.AssertUtils;
import org.apache.shardingsphere.example.core.api.trace.DatabaseAccess;
import org.apache.shardingsphere.example.core.api.trace.MemoryLogService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
/**
 * @author wanghaitao
 * @date 2019/12/13 17:18
 */
public class SpringBootJpaAssertUtils implements AssertUtils {

    public static void assertShardingTableDatabases(ExampleService exampleService){
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
    }

    public static void assertMasterSlaves(ExampleService exampleService){
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
    }

    public static void assertShardingMasterSlaves(ExampleService exampleService){
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
    }

}
