package com.clickaway.service;

import com.clickaway.entity.Campaign;
import com.clickaway.service.dto.CampaignDTO;

import java.util.List;
import java.util.Optional;

public interface CampaignService {
    List<CampaignDTO> addCampaign(List<Campaign> campaigns);

    Optional<CampaignDTO> getCampaign(Long id);

    List<CampaignDTO> getAllCampaigns();

    void deleteCampaign(Long id);
}