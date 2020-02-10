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

package org.apache.shardingsphere.example.core.api.senario;


import org.apache.shardingsphere.example.core.api.service.TransactionService;

import java.sql.SQLException;

public final class TransactionServiceScenario implements Scenario {
    
    private final TransactionService transactionService;
    
    public TransactionServiceScenario(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    public TransactionService getTransactionService() {
        return transactionService;
    }
    
    @Override
    public void process() throws SQLException {
        try {
            transactionService.initEnvironment();
            transactionService.processSuccessWithLocal();
            transactionService.processSuccessWithXA();
            transactionService.processSuccessWithBase();
            transactionService.processFailureWithLocal();
            transactionService.processFailureWithXA();
            transactionService.processFailureWithBase();
        } finally {
            transactionService.cleanEnvironment();
        }
    }
}
