package com.settlement.core.service;

import com.settlement.core.entity.SubscriptionOrder;
import com.settlement.core.entity.SubscriptionOrder.OrderStatus;
import com.settlement.core.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AccountService accountService;

    private static final BigDecimal FIXED_NAV = BigDecimal.ONE;

    public String submitSubscription(String accountId, String productId, BigDecimal amount) {
        String orderId = UUID.randomUUID().toString();
        SubscriptionOrder order = new SubscriptionOrder(orderId, accountId, productId, amount);
        subscriptionRepository.save(order);
        return orderId;
    }

    public void confirmSubscription(String orderId) {
        SubscriptionOrder order = subscriptionRepository.findSubscriptionById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在: " + orderId);
        }
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("订单状态异常: " + order.getStatus());
        }
        BigDecimal shares = order.getAmount().divide(FIXED_NAV);
        accountService.deductBalance(order.getAccountId(), order.getAmount());
        accountService.addShares(order.getAccountId(), shares);
        order.setShares(shares);
        order.setNav(FIXED_NAV);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmTime(java.time.LocalDateTime.now());
        subscriptionRepository.save(order);
    }
}
