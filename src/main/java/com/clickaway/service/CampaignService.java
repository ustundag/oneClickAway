package com.clickaway.service;

import com.clickaway.model.entity.Campaign;
import com.clickaway.service.dto.CampaignDTO;

import java.util.List;

public interface CampaignService {
    CampaignDTO addCampaign(Campaign campaign);

    CampaignDTO getCampaign(Long id);

    List<CampaignDTO> getAllCampaigns();

    void deleteCampaign(Long id);
}