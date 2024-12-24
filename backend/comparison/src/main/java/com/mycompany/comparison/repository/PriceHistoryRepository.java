package com.mycompany.comparison.repository;

import com.mycompany.comparison.entity.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    List<PriceHistory> findByProductId(Long productId);
}
