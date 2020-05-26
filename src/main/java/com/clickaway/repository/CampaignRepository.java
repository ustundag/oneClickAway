package com.clickaway.repository;

import com.clickaway.model.entity.Campaign;
import com.clickaway.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByCategoryIdAndItemLimitIsLessThanEqual(Long catId, int itemLimit);
}
