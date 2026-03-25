package com.settlement.core.service;

import com.settlement.core.entity.SubscriptionOrder;
import com.settlement.core.entity.RedemptionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 清算服务 - 负责资金和份额的最终清算
 */
@Service
public class SettlementService {

    @Autowired
    private AccountService accountService;

    /**
     * 解冻赎回资金（清算完成后调用）
     * @param accountId 账户ID
     * @param amount 解冻金额
     */
    public void unfreezeRedemptionFund(String accountId, BigDecimal amount) {
        // 1. 减少冻结金额
        accountService.unfreezeBalance(accountId, amount);

        // 2. 增加可用余额（资金到账）
        accountService.addBalance(accountId, amount);
    }

    /**
     * 获取账户资产快照
     * @param accountId 账户ID
     * @return 资产明细
     */
    public Map<String, Object> getAccountSnapshot(String accountId) {
        Map<String, Object> snapshot = new HashMap<>();
        snapshot.put("accountId", accountId);
        snapshot.put("availableBalance", accountService.getAvailableBalance(accountId));
        snapshot.put("frozenBalance", accountService.getFrozenBalance(accountId));
        snapshot.put("totalShares", accountService.getTotalShares(accountId));
        return snapshot;
    }
}
