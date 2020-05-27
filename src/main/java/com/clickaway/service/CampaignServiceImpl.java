package com.clickaway.service;

import com.clickaway.entity.Campaign;
import com.clickaway.repository.CampaignRepository;
import com.clickaway.service.dto.CampaignDTO;
import com.clickaway.transformer.CampaignTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignTransformer campaignTransformer;

    @Override
    public CampaignDTO addCampaign(Campaign campaign) {
        Campaign campaign_saved = campaignRepository.save(campaign);
        return campaignTransformer.transformToCampaignDTO(campaign_saved);
    }

    @Override
    public CampaignDTO getCampaign(Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        return campaign.map(obj -> campaignTransformer.transformToCampaignDTO(obj))
                .orElse(null);
    }

    @Override
    public List<CampaignDTO> getAllCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        List<CampaignDTO> campaignDTOs = new ArrayList<>();
        campaigns.forEach(item -> {
            CampaignDTO campaignDTO = campaignTransformer.transformToCampaignDTO(item);
            campaignDTOs.add(campaignDTO);
        });
        return campaignDTOs;
    }

    @Override
    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}