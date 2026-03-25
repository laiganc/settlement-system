package com.settlement.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 基金净值实体
 */
public class NAV {
    private String productId;   // 产品ID
    private LocalDate navDate;  // 净值日期
    private BigDecimal nav;     // 单位净值

    public NAV() {}

    public NAV(String productId, LocalDate navDate, BigDecimal nav) {
        this.productId = productId;
        this.navDate = navDate;
        this.nav = nav;
    }

    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public LocalDate getNavDate() { return navDate; }
    public void setNavDate(LocalDate navDate) { this.navDate = navDate; }
    public BigDecimal getNav() { return nav; }
    public void setNav(BigDecimal nav) { this.nav = nav; }
}
