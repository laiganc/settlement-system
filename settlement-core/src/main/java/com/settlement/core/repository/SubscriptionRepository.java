package com.settlement.core.repository;

import com.settlement.core.entity.SubscriptionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionOrder, String> {
    Optional<SubscriptionOrder> findByOrderId(String orderId);
    List<SubscriptionOrder> findByAccountId(String accountId);
    
    default SubscriptionOrder findSubscriptionById(String orderId) {
        return findById(orderId).orElse(null);
    }
}
