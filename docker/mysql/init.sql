CREATE DATABASE IF NOT EXISTS settlement CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE settlement;
CREATE TABLE IF NOT EXISTS account (
    account_id VARCHAR(64) PRIMARY KEY COMMENT '账户ID',
    account_name VARCHAR(128) NOT NULL COMMENT '账户名称',
    available_balance DECIMAL(20,4) DEFAULT 0.0000 COMMENT '可用余额',
    frozen_balance DECIMAL(20,4) DEFAULT 0.0000 COMMENT '冻结金额',
    total_shares DECIMAL(20,4) DEFAULT 0.0000 COMMENT '持有份额',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_account_name (account_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表';
CREATE TABLE IF NOT EXISTS product (
    product_id VARCHAR(64) PRIMARY KEY COMMENT '产品ID',
    product_name VARCHAR(256) NOT NULL COMMENT '产品名称',
    product_type VARCHAR(32) NOT NULL COMMENT '产品类型',
    nav DECIMAL(10,6) DEFAULT 1.000000 COMMENT '单位净值',
    nav_date DATE COMMENT '净值日期',
    status VARCHAR(16) NOT NULL DEFAULT 'OPEN' COMMENT '状态',
    min_subscription DECIMAL(20,4) DEFAULT 0.0001 COMMENT '最小申购金额',
    min_redemption DECIMAL(20,4) DEFAULT 0.0001 COMMENT '最小赎回份额',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_product_type (product_type),
    INDEX idx_nav_date (nav_date),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品表';
CREATE TABLE IF NOT EXISTS nav_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(64) NOT NULL COMMENT '产品ID',
    nav_date DATE NOT NULL COMMENT '净值日期',
    nav DECIMAL(10,6) NOT NULL COMMENT '单位净值',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_product_nav_date (product_id, nav_date),
    INDEX idx_nav_date (nav_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='净值记录表';
CREATE TABLE IF NOT EXISTS subscription_order (
    order_id VARCHAR(64) PRIMARY KEY COMMENT '订单ID',
    account_id VARCHAR(64) NOT NULL COMMENT '账户ID',
    product_id VARCHAR(64) NOT NULL COMMENT '产品ID',
    amount DECIMAL(20,4) NOT NULL COMMENT '申购金额',
    shares DECIMAL(20,4) COMMENT '确认份额',
    nav DECIMAL(10,6) COMMENT '确认净值',
    status VARCHAR(16) NOT NULL DEFAULT 'PENDING' COMMENT '状态',
    submit_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
    confirm_time DATETIME COMMENT '确认时间',
    INDEX idx_account_id (account_id),
    INDEX idx_product_id (product_id),
    INDEX idx_status (status),
    INDEX idx_submit_time (submit_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='申购订单表';
CREATE TABLE IF NOT EXISTS redemption_order (
    order_id VARCHAR(64) PRIMARY KEY COMMENT '订单ID',
    account_id VARCHAR(64) NOT NULL COMMENT '账户ID',
    product_id VARCHAR(64) NOT NULL COMMENT '产品ID',
    shares DECIMAL(20,4) NOT NULL COMMENT '赎回份额',
    amount DECIMAL(20,4) COMMENT '确认金额',
    nav DECIMAL(10,6) COMMENT '确认净值',
    status VARCHAR(16) NOT NULL DEFAULT 'PENDING' COMMENT '状态',
    submit_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
    confirm_time DATETIME COMMENT '确认时间',
    INDEX idx_account_id (account_id),
    INDEX idx_product_id (product_id),
    INDEX idx_status (status),
    INDEX idx_submit_time (submit_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='赎回订单表';
INSERT INTO account (account_id, account_name, available_balance, frozen_balance, total_shares) VALUES ('ACC001', '测试账户1', 100000.0000, 0.0000, 0.0000);
INSERT INTO product (product_id, product_name, product_type, nav, nav_date, status) VALUES ('PRD001', '测试公募基金', 'PUBLIC', 1.000000, CURDATE(), 'OPEN');
