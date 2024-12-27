package com.mycompany.comparison.service;

import com.mycompany.comparison.entity.PriceHistory;
import com.mycompany.comparison.repository.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceHistoryService {

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    // 获取商品的历史价格记录
    public List<PriceHistory> getPriceHistoryByProductId(Long productId) {
        System.out.println("getPriceHistoryByProductId :  " + productId);
        return priceHistoryRepository.findByProductId(productId);
    }

    // 保存新的历史价格记录
    public PriceHistory addPriceHistory(Long productId, String productUrl, double price) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setProductId(productId);
        priceHistory.setProductUrl(productUrl);
        priceHistory.setPrice(price);
        priceHistory.setTimestamp(new java.util.Date());
        return priceHistoryRepository.save(priceHistory);
    }
}
