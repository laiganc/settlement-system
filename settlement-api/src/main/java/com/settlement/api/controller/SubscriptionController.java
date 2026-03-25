package com.settlement.api.controller;

import com.settlement.core.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 申购控制器
 */
@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * 提交申购订单
     * POST /api/subscription/submit
     */
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitSubscription(
            @RequestParam("accountId") String accountId,
            @RequestParam("productId") String productId,
            @RequestParam("amount") BigDecimal amount) {

        String orderId = subscriptionService.submitSubscription(accountId, productId, amount);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("orderId", orderId);
        response.put("message", "申购订单提交成功");

        return ResponseEntity.ok(response);
    }

    /**
     * 确认申购订单
     * POST /api/subscription/confirm
     */
    @PostMapping("/confirm")
    public ResponseEntity<Map<String, Object>> confirmSubscription(@RequestParam("orderId") String orderId) {
        try {
            subscriptionService.confirmSubscription(orderId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("orderId", orderId);
            response.put("message", "申购订单确认成功");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}
