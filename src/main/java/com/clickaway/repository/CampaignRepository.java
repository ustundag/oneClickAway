package com.clickaway.repository;

import com.clickaway.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByCategoryIdAndItemLimitIsLessThanEqual(Long catId, int itemLimit);
}
