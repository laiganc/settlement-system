package com.settlement.core.service;

import com.settlement.core.entity.Account;
import com.settlement.core.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 账户服务
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // ========== 余额操作 ==========

    /**
     * 增加可用余额
     */
    public void addBalance(String accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        account.setAvailableBalance(account.getAvailableBalance().add(amount));
        accountRepository.save(account);
    }

    /**
     * 扣除可用余额
     */
    public void deductBalance(String accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        if (account.getAvailableBalance().compareTo(amount) < 0) {
            throw new RuntimeException("可用余额不足");
        }
        account.setAvailableBalance(account.getAvailableBalance().subtract(amount));
        accountRepository.save(account);
    }

    /**
     * 冻结资金
     */
    public void freezeBalance(String accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        account.setFrozenBalance(account.getFrozenBalance().add(amount));
        accountRepository.save(account);
    }

    /**
     * 解冻资金
     */
    public void unfreezeBalance(String accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        if (account.getFrozenBalance().compareTo(amount) < 0) {
            throw new RuntimeException("冻结余额不足");
        }
        account.setFrozenBalance(account.getFrozenBalance().subtract(amount));
        accountRepository.save(account);
    }

    // ========== 份额操作 ==========

    /**
     * 增加份额
     */
    public void addShares(String accountId, BigDecimal shares) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        account.setTotalShares(account.getTotalShares().add(shares));
        accountRepository.save(account);
    }

    /**
     * 扣除份额
     */
    public void deductShares(String accountId, BigDecimal shares) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        if (account.getTotalShares().compareTo(shares) < 0) {
            throw new RuntimeException("持有份额不足");
        }
        account.setTotalShares(account.getTotalShares().subtract(shares));
        accountRepository.save(account);
    }

    // ========== 查询操作 ==========

    public BigDecimal getAvailableBalance(String accountId) {
        Account account = accountRepository.findById(accountId);
        return account != null ? account.getAvailableBalance() : BigDecimal.ZERO;
    }

    public BigDecimal getFrozenBalance(String accountId) {
        Account account = accountRepository.findById(accountId);
        return account != null ? account.getFrozenBalance() : BigDecimal.ZERO;
    }

    public BigDecimal getTotalShares(String accountId) {
        Account account = accountRepository.findById(accountId);
        return account != null ? account.getTotalShares() : BigDecimal.ZERO;
    }
}
