package com.settlement.core.repository;

import com.settlement.core.entity.SubscriptionOrder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 申购订单仓储 - 内存Map实现
 */
@Repository
public class SubscriptionRepository {

    private final Map<String, SubscriptionOrder> store = new ConcurrentHashMap<>();

    public SubscriptionOrder findById(String orderId) {
        return store.get(orderId);
    }

    public void save(SubscriptionOrder order) {
        store.put(order.getOrderId(), order);
    }
}
