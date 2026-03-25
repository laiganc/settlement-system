package com.settlement.core.repository;

import com.settlement.core.entity.RedemptionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RedemptionRepository extends JpaRepository<RedemptionOrder, String> {
    Optional<RedemptionOrder> findByOrderId(String orderId);
    List<RedemptionOrder> findByAccountId(String accountId);
    
    default RedemptionOrder findRedemptionById(String orderId) {
        return findById(orderId).orElse(null);
    }
}
