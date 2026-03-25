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

    public void addBalance(String accountId, BigDecimal amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        account.setAvailableBalance(account.getAvailableBalance().add(amount));
        accountRepository.save(account);
    }

    public void deductBalance(String accountId, BigDecimal amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        if (account.getAvailableBalance().compareTo(amount) < 0) {
            throw new RuntimeException("可用余额不足");
        }
        account.setAvailableBalance(account.getAvailableBalance().subtract(amount));
        accountRepository.save(account);
    }

    public void freezeBalance(String accountId, BigDecimal amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        account.setFrozenBalance(account.getFrozenBalance().add(amount));
        accountRepository.save(account);
    }

    public void unfreezeBalance(String accountId, BigDecimal amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        if (account.getFrozenBalance().compareTo(amount) < 0) {
            throw new RuntimeException("冻结余额不足");
        }
        account.setFrozenBalance(account.getFrozenBalance().subtract(amount));
        accountRepository.save(account);
    }

    public void addShares(String accountId, BigDecimal shares) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        account.setTotalShares(account.getTotalShares().add(shares));
        accountRepository.save(account);
    }

    public void deductShares(String accountId, BigDecimal shares) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new RuntimeException("账户不存在: " + accountId);
        }
        if (account.getTotalShares().compareTo(shares) < 0) {
            throw new RuntimeException("持有份额不足");
        }
        account.setTotalShares(account.getTotalShares().subtract(shares));
        accountRepository.save(account);
    }

    public BigDecimal getAvailableBalance(String accountId) {
        Account account = accountRepository.findAccountById(accountId);
        return account != null ? account.getAvailableBalance() : BigDecimal.ZERO;
    }

    public BigDecimal getFrozenBalance(String accountId) {
        Account account = accountRepository.findAccountById(accountId);
        return account != null ? account.getFrozenBalance() : BigDecimal.ZERO;
    }

    public BigDecimal getTotalShares(String accountId) {
        Account account = accountRepository.findAccountById(accountId);
        return account != null ? account.getTotalShares() : BigDecimal.ZERO;
    }
}
