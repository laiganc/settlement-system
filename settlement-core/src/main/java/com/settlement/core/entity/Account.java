package com.settlement.core.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id", length = 64)
    private String accountId;

    @Column(name = "account_name", length = 128)
    private String accountName;

    @Column(name = "available_balance", precision = 18, scale = 2)
    private BigDecimal availableBalance;

    @Column(name = "frozen_balance", precision = 18, scale = 2)
    private BigDecimal frozenBalance;

    @Column(name = "total_shares", precision = 18, scale = 2)
    private BigDecimal totalShares;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public Account() {}

    public Account(String accountId, String accountName, BigDecimal initialBalance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.availableBalance = initialBalance;
        this.frozenBalance = BigDecimal.ZERO;
        this.totalShares = BigDecimal.ZERO;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

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
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}
