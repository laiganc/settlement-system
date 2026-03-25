package com.settlement.core.service;

import com.settlement.core.entity.SubscriptionOrder;
import com.settlement.core.entity.SubscriptionOrder.OrderStatus;
import com.settlement.core.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 申购服务
 */
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AccountService accountService;

    private static final BigDecimal FIXED_NAV = BigDecimal.ONE; // 固定净值1.0

    /**
     * 提交申购订单
     * @param accountId 账户ID
     * @param productId 产品ID
     * @param amount 申购金额
     * @return 订单ID
     */
    public String submitSubscription(String accountId, String productId, BigDecimal amount) {
        // 1. 生成订单ID
        String orderId = UUID.randomUUID().toString();

        // 2. 创建订单
        SubscriptionOrder order = new SubscriptionOrder(orderId, accountId, productId, amount);

        // 3. 保存订单
        subscriptionRepository.save(order);

        return orderId;
    }

    /**
     * 确认申购订单
     * @param orderId 订单ID
     */
    public void confirmSubscription(String orderId) {
        // 1. 查询订单
        SubscriptionOrder order = subscriptionRepository.findById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在: " + orderId);
        }

        // 2. 检查订单状态
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("订单状态异常: " + order.getStatus());
        }

        // 3. 计算份额 = 金额 / 净值（净值固定1.0，所以份额=金额）
        BigDecimal shares = order.getAmount().divide(FIXED_NAV);

        // 4. 扣款（从账户可用余额扣除）
        accountService.deductBalance(order.getAccountId(), order.getAmount());

        // 5. 登记份额（增加账户持有份额）
        accountService.addShares(order.getAccountId(), shares);

        // 6. 更新订单状态
        order.setShares(shares);
        order.setNav(FIXED_NAV);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmTime(java.time.LocalDateTime.now());
        subscriptionRepository.save(order);
    }
}
