package com.settlement.core.repository;

import com.settlement.core.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByAccountId(String accountId);
    
    default Account findAccountById(String accountId) {
        return findById(accountId).orElse(null);
    }
}
