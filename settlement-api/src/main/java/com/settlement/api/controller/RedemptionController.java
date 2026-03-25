package com.settlement.api.controller;

import com.settlement.core.service.RedemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 赎回控制器
 */
@RestController
@RequestMapping("/api/redemption")
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    /**
     * 提交赎回订单
     * POST /api/redemption/submit
     */
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitRedemption(
            @RequestParam("accountId") String accountId,
            @RequestParam("productId") String productId,
            @RequestParam("shares") BigDecimal shares) {

        String orderId = redemptionService.submitRedemption(accountId, productId, shares);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("orderId", orderId);
        response.put("message", "赎回订单提交成功");

        return ResponseEntity.ok(response);
    }

    /**
     * 确认赎回订单
     * POST /api/redemption/confirm
     */
    @PostMapping("/confirm")
    public ResponseEntity<Map<String, Object>> confirmRedemption(@RequestParam("orderId") String orderId) {
        try {
            redemptionService.confirmRedemption(orderId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("orderId", orderId);
            response.put("message", "赎回订单确认成功");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}
