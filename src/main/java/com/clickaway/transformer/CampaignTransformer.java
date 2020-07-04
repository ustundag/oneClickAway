package com.clickaway.transformer;

import com.clickaway.entity.Campaign;
import com.clickaway.service.dto.CampaignDTO;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class CampaignTransformer extends AbstractTransformer {

    public CampaignDTO transformToCampaignDTO(Campaign campaign) {
        URI uri = createUri(campaign.getId(), "campaign");
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
