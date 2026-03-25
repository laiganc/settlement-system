package com.settlement.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账户实体
 */
public class Account {
    private String accountId;           // 账户ID
    private String accountName;         // 账户名称
    private BigDecimal availableBalance;// 可用余额
    private BigDecimal frozenBalance;   // 冻结金额（赎回时冻结）
    private BigDecimal totalShares;     // 持有份额
    private LocalDateTime createTime;

    public Account() {}

    public Account(String accountId, String accountName, BigDecimal initialBalance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.availableBalance = initialBalance;
        this.frozenBalance = BigDecimal.ZERO;
        this.totalShares = BigDecimal.ZERO;
        this.createTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    public BigDecimal getAvailableBalance() { return availableBalance; }
    public void setAvailableBalance(BigDecimal availableBalance) { this.availableBalance = availableBalance; }
    public BigDecimal getFrozenBalance() { return frozenBalance; }
    public void setFrozenBalance(BigDecimal frozenBalance) { this.frozenBalance = frozenBalance; }
    public BigDecimal getTotalShares() { return totalShares; }
    public void setTotalShares(BigDecimal totalShares) { this.totalShares = totalShares; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
