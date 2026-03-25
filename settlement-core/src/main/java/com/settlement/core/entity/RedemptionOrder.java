package com.settlement.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 赎回订单实体
 */
public class RedemptionOrder {
    private String orderId;         // 订单ID
    private String accountId;       // 账户ID
    private String productId;       // 产品ID
    private BigDecimal shares;      // 赎回份额
    private BigDecimal amount;     // 赎回金额
    private BigDecimal nav;         // 确认净值
    private OrderStatus status;     // 订单状态
    private LocalDateTime createTime;
    private LocalDateTime confirmTime;

    public enum OrderStatus {
        PENDING,    // 待确认
        CONFIRMED,  // 已确认
        CANCELLED   // 已取消
    }

    public RedemptionOrder() {}

    public RedemptionOrder(String orderId, String accountId, String productId, BigDecimal shares) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.productId = productId;
        this.shares = shares;
        this.amount = BigDecimal.ZERO;
        this.nav = BigDecimal.ONE; // 固定净值1.0
        this.status = OrderStatus.PENDING;
        this.createTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public BigDecimal getShares() { return shares; }
    public void setShares(BigDecimal shares) { this.shares = shares; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getNav() { return nav; }
    public void setNav(BigDecimal nav) { this.nav = nav; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getConfirmTime() { return confirmTime; }
    public void setConfirmTime(LocalDateTime confirmTime) { this.confirmTime = confirmTime; }
}
