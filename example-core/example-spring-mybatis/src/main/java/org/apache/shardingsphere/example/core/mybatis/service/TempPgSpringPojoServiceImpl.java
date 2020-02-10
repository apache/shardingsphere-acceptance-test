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

package org.apache.shardingsphere.example.core.mybatis.service;

import org.apache.shardingsphere.example.core.api.entity.Order;
import org.apache.shardingsphere.example.core.api.entity.OrderItem;
import org.apache.shardingsphere.example.core.api.repository.OrderItemRepository;
import org.apache.shardingsphere.example.core.api.repository.OrderRepository;
import org.apache.shardingsphere.example.core.api.service.TempPgCommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TempPgSpringPojoServiceImpl extends TempPgCommonServiceImpl implements TempPgSpringPojoService {
    
    @Resource
    private OrderRepository orderRepository;
    
    @Resource
    private OrderItemRepository orderItemRepository;
    
    @Override
    protected OrderRepository getOrderRepository() {
        return orderRepository;
    }
    
    @Override
    protected OrderItemRepository getOrderItemRepository() {
        return orderItemRepository;
    }
    
    @Override
    protected Order newOrder() {
        return new Order();
    }
    
    @Override
    protected OrderItem newOrderItem() {
        return new OrderItem();
    }
}
