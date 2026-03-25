package com.settlement.core.repository;

import com.settlement.core.entity.Account;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 账户仓储 - 内存Map实现
 */
@Repository
public class AccountRepository {

    private final Map<String, Account> store = new ConcurrentHashMap<>();

    public AccountRepository() {
        // 初始化一个测试账户
        Account testAccount = new Account("ACC001", "测试账户", new BigDecimal("10000"));
        store.put("ACC001", testAccount);
    }

    public Account findById(String accountId) {
        return store.get(accountId);
    }

    public void save(Account account) {
        store.put(account.getAccountId(), account);
    }

    public Account createAccount(String accountName, BigDecimal initialBalance) {
        String accountId = UUID.randomUUID().toString();
        Account account = new Account(accountId, accountName, initialBalance);
        store.put(accountId, account);
        return account;
    }
}
