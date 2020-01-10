/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.example.core.mybatis.common;


import org.apache.shardingsphere.example.core.api.service.CommonService;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.api.service.TransactionService;
import org.apache.shardingsphere.example.core.api.trace.AssertUtils;
import org.apache.shardingsphere.example.core.api.trace.DatabaseAccess;
import org.apache.shardingsphere.example.core.api.trace.MemoryLogService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SpringResultAssertUtils implements AssertUtils {
    
    public static void assertShardingDatabaseResult(final CommonService commonService) {
        MemoryLogService memoryLogService = commonService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertShardingTableResult(final CommonService commonService) {
        MemoryLogService memoryLogService = commonService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertShardingDatabaseAndTableResult(final CommonService commonService) {
        MemoryLogService memoryLogService = commonService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertMasterSlaveResult(final CommonService commonService) {
        MemoryLogService memoryLogService = commonService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(0));
    }
    
    public static void assertExampleServiceMasterSlaveResult(final ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(0));
    }

    public static void assertExampleServiceShardingMasterSlaveResult(final ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(0));
    }
    
    public static void assertShardingMasterSlaveResult(final CommonService commonService) {
        MemoryLogService memoryLogService = commonService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(0));
    }
    
    public static void assertEncryptResult(CommonService commonService) {
        MemoryLogService memoryLogService = commonService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertTransactionServiceResult(final TransactionService transactionService) {
        MemoryLogService memoryLogService = transactionService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(60));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(30));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(60));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(30));
    }
    
    public static void assertTransactionMasterSlaveResult(final TransactionService transactionService) {
        MemoryLogService memoryLogService = transactionService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(40));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(20));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(40));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(20));
    }
    
    public static void assertShardingMasterSlaveEncryptResult(CommonService commonService) {
        MemoryLogService memoryLogService = commonService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(0));
    }
    
    public static void assertExampleServiceEncryptResult(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getUserData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getUserData(DatabaseAccess.SELECT).size(), is(10));
    }

    public static void assertExampleServiceShardingEncryptResult(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getUserData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getUserData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertExampleServiceMasterSlaveEncryptResult(ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getUserData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getUserData(DatabaseAccess.SELECT).size(), is(0));
    }
    
    public static void assertTempPgShardingDatabaseAndTableResult(final CommonService commonService) {
        MemoryLogService memoryLogService = commonService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(20));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(20));
    }
    
    public static void assertExampleServiceShardingDatabaseAndTableResult(final ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertTempPgExampleServiceShardingDatabaseAndTableResult(final ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
    }
    
    public static void assertExampleServiceShardingDatabaseResult(final ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
    }
    
    public static void assertTempPgExampleServiceShardingDatabaseResult(final ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(0));
    }
    
    public static void assertExampleServiceShardingTableResult(final  ExampleService exampleService) {
        MemoryLogService memoryLogService = exampleService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
    }
}
