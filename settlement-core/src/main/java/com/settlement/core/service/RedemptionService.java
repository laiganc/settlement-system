package com.settlement.core.service;

import com.settlement.core.entity.RedemptionOrder;
import com.settlement.core.entity.RedemptionOrder.OrderStatus;
import com.settlement.core.repository.RedemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class RedemptionService {

    @Autowired
    private RedemptionRepository redemptionRepository;

    @Autowired
    private AccountService accountService;

    private static final BigDecimal FIXED_NAV = BigDecimal.ONE;

    public String submitRedemption(String accountId, String productId, BigDecimal shares) {
        String orderId = UUID.randomUUID().toString();
        RedemptionOrder order = new RedemptionOrder(orderId, accountId, productId, shares);
        redemptionRepository.save(order);
        return orderId;
    }

    public void confirmRedemption(String orderId) {
        RedemptionOrder order = redemptionRepository.findRedemptionById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在: " + orderId);
        }
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("订单状态异常: " + order.getStatus());
        }
        BigDecimal amount = order.getShares().multiply(FIXED_NAV);
        accountService.deductShares(order.getAccountId(), order.getShares());
        accountService.freezeBalance(order.getAccountId(), amount);
        order.setAmount(amount);
        order.setNav(FIXED_NAV);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmTime(java.time.LocalDateTime.now());
        redemptionRepository.save(order);
    }
}
