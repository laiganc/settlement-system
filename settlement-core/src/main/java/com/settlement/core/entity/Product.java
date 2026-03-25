package com.settlement.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 基金产品实体
 */
public class Product {
    private String productId;       // 产品ID
    private String productName;    // 产品名称
    private BigDecimal nav;        // 单位净值（简化为固定1.0）
    private LocalDateTime createTime;

    public Product() {}

    public Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
        this.nav = BigDecimal.ONE; // 固定净值 1.0
        this.createTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public BigDecimal getNav() { return nav; }
    public void setNav(BigDecimal nav) { this.nav = nav; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}
