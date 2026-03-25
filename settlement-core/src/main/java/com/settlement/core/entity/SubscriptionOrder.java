package com.settlement.core.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscription_order")
public class SubscriptionOrder {

    public enum OrderStatus {
        PENDING, CONFIRMED, CANCELLED
    }

    @Id
    @Column(name = "order_id", length = 64)
    private String orderId;

    @Column(name = "account_id", length = 64)
    private String accountId;

    @Column(name = "product_id", length = 64)
    private String productId;

    @Column(name = "amount", precision = 20, scale = 4)
    private BigDecimal amount;

    @Column(name = "shares", precision = 20, scale = 4)
    private BigDecimal shares;

    @Column(name = "nav", precision = 10, scale = 6)
    private BigDecimal nav;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 16)
    private OrderStatus status;

    @Column(name = "submit_time")
    private LocalDateTime submitTime;

    @Column(name = "confirm_time")
    private LocalDateTime confirmTime;

    public SubscriptionOrder() {}

    public SubscriptionOrder(String orderId, String accountId, String productId, BigDecimal amount) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.productId = productId;
        this.amount = amount;
        this.status = OrderStatus.PENDING;
        this.submitTime = LocalDateTime.now();
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getShares() { return shares; }
    public void setShares(BigDecimal shares) { this.shares = shares; }
    public BigDecimal getNav() { return nav; }
    public void setNav(BigDecimal nav) { this.nav = nav; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public LocalDateTime getSubmitTime() { return submitTime; }
    public void setSubmitTime(LocalDateTime submitTime) { this.submitTime = submitTime; }
    public LocalDateTime getConfirmTime() { return confirmTime; }
    public void setConfirmTime(LocalDateTime confirmTime) { this.confirmTime = confirmTime; }
}
