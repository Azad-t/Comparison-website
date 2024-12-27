package com.mycompany.comparison.controller;

import com.mycompany.comparison.entity.PriceHistory;
import com.mycompany.comparison.service.PriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price-history")
public class PriceHistoryController {

    @Autowired
    private PriceHistoryService priceHistoryService;

    // 获取指定商品的历史价格
    @GetMapping("/{productId}")
    public List<PriceHistory> getPriceHistory(@PathVariable Long productId) {
        System.out.println("获取指定商品的历史价格 productId: " + productId);
        return priceHistoryService.getPriceHistoryByProductId(productId);
    }
}