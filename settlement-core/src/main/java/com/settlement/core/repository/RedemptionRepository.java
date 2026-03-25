package com.settlement.core.repository;

import com.settlement.core.entity.RedemptionOrder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 赎回订单仓储 - 内存Map实现
 */
@Repository
public class RedemptionRepository {

    private final Map<String, RedemptionOrder> store = new ConcurrentHashMap<>();

    public RedemptionOrder findById(String orderId) {
        return store.get(orderId);
    }

    public void save(RedemptionOrder order) {
        store.put(order.getOrderId(), order);
    }
}
