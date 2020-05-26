package com.clickaway.transformer;

import com.clickaway.entity.Campaign;
import com.clickaway.service.dto.CampaignDTO;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class CampaignTransformer extends AbstractTransformer {

    public Campaign transformToCampaign(CampaignDTO campaignDTO) {
        Campaign campaign = new Campaign();
        campaign.setTitle(campaignDTO.getTitle());
        campaign.setItemLimit(campaignDTO.getItemLimit());
        campaign.setCategoryId(campaignDTO.getCategoryId());
        campaign.setDiscount(campaignDTO.getDiscount());
        campaign.setDiscountType(campaignDTO.getDiscountType());
        return campaign;
    }

    public CampaignDTO transformToCampaignDTO(Campaign campaign) {
        URI uri = createUri(campaign.getId());
        CampaignDTO campaignDTO = CampaignDTO.builder()
                .itemLimit(campaign.getItemLimit())
                .categoryId(campaign.getCategoryId())
                .discount(campaign.getDiscount())
                .discountType(campaign.getDiscountType())
                .uri(uri).build();
        campaignDTO.setId(campaign.getId());
        campaignDTO.setTitle(campaign.getTitle());
        return campaignDTO;
    }

}
