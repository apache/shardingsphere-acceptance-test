package org.apache.shardingsphere.example.core.jdbc.common;

import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.api.trace.AssertUtils;
import org.apache.shardingsphere.example.core.api.trace.DatabaseAccess;
import org.apache.shardingsphere.example.core.api.trace.MemoryLogService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * linzesi
 */
public class RawJdbcAssertUtils implements AssertUtils {
    
    public static void assertShardingTableDatabases(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
    }
    
    public static void assertMasterSlaves(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
    }
    
    public static void assertShardingMasterSlaves(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(0));
    }
    
    public static void assertEncrypt(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getUserData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getUserData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertShardingEncrypt(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getUserData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getUserData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertShardingMasterSlaveEncrypt(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getUserData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getUserData(DatabaseAccess.SELECT).size(), is(0));
    }
}
