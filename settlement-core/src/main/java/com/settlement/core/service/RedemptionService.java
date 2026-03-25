package com.settlement.core.service;

import com.settlement.core.entity.RedemptionOrder;
import com.settlement.core.entity.RedemptionOrder.OrderStatus;
import com.settlement.core.repository.RedemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 赎回服务
 */
@Service
public class RedemptionService {

    @Autowired
    private RedemptionRepository redemptionRepository;

    @Autowired
    private AccountService accountService;

    private static final BigDecimal FIXED_NAV = BigDecimal.ONE; // 固定净值1.0

    /**
     * 提交赎回订单
     * @param accountId 账户ID
     * @param productId 产品ID
     * @param shares 赎回份额
     * @return 订单ID
     */
    public String submitRedemption(String accountId, String productId, BigDecimal shares) {
        // 1. 生成订单ID
        String orderId = UUID.randomUUID().toString();

        // 2. 创建订单
        RedemptionOrder order = new RedemptionOrder(orderId, accountId, productId, shares);

        // 3. 保存订单
        redemptionRepository.save(order);

        return orderId;
    }

    /**
     * 确认赎回订单
     * @param orderId 订单ID
     */
    public void confirmRedemption(String orderId) {
        // 1. 查询订单
        RedemptionOrder order = redemptionRepository.findById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在: " + orderId);
        }

        // 2. 检查订单状态
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("订单状态异常: " + order.getStatus());
        }

        // 3. 计算赎回金额 = 份额 * 净值（净值固定1.0，所以金额=份额）
        BigDecimal amount = order.getShares().multiply(FIXED_NAV);

        // 4. 扣份额（从账户持有份额中扣除）
        accountService.deductShares(order.getAccountId(), order.getShares());

        // 5. 冻结资金（资金暂时冻结，后续清算后解冻）
        accountService.freezeBalance(order.getAccountId(), amount);

        // 6. 更新订单状态
        order.setAmount(amount);
        order.setNav(FIXED_NAV);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmTime(java.time.LocalDateTime.now());
        redemptionRepository.save(order);
    }
}
