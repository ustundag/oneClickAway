package com.clickaway.service;

import com.clickaway.entity.Campaign;
import com.clickaway.repository.CampaignRepository;
import com.clickaway.service.dto.CampaignDTO;
import com.clickaway.transformer.CampaignTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
class CampaignServiceImplTest {

    @InjectMocks
    private CampaignServiceImpl campaignService;
    @Mock
    private CampaignRepository campaignRepository;
    @Mock
    private CampaignTransformer campaignTransformer;

    @Test
    void addCampaign() {
        Long id = 1L;
        String title = "clothes";
        // given
        Campaign campaign = new Campaign();
        campaign.setId(id);
        campaign.setTitle(title);

        // when
        Campaign campaignMock = mock(Campaign.class);
        when(campaignMock.getId()).thenReturn(id);
        when(campaignMock.getTitle()).thenReturn(title);
        when(campaignRepository.save(any(Campaign.class)))
                .thenReturn(campaignMock);

        CampaignDTO campaignMockDTO = mock(CampaignDTO.class);
        when(campaignMockDTO.getId()).thenReturn(id);
        when(campaignMockDTO.getTitle()).thenReturn(title);
        when(campaignTransformer.transformToCampaignDTO(any(Campaign.class)))
                .thenReturn(campaignMockDTO);

        // then
        CampaignDTO campaignDTO = campaignService.addCampaign(campaign);
        assertEquals(campaignDTO.getId(), id);
        assertEquals(campaignDTO.getTitle(), title);
    }

    @Test
    void getCampaign() {
        // given
        Long id = 1L;

        // when
        Campaign campaignMock = mock(Campaign.class);
        when(campaignRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(campaignMock));
        when(campaignMock.getId()).thenReturn(id);

        CampaignDTO campaignMockDTO = mock(CampaignDTO.class);
        when(campaignMockDTO.getId()).thenReturn(id);
        when(campaignTransformer.transformToCampaignDTO(any(Campaign.class)))
                .thenReturn(campaignMockDTO);

        // then
        CampaignDTO campaignDTO = campaignService.getCampaign(id);
        assertEquals(campaignDTO.getId(), id);
    }

    @Test
    void getAllCampaigns() {
        List<Campaign> campaignList = new ArrayList<>();
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaignList.add(campaign);
        campaign = new Campaign();
        campaign.setId(2L);
        campaignList.add(campaign);

        // given

        // when
        when(campaignRepository.findAll()).thenReturn(campaignList);
        CampaignDTO campaignMockDTO_1 = mock(CampaignDTO.class);
        CampaignDTO campaignMockDTO_2 = mock(CampaignDTO.class);
        when(campaignMockDTO_1.getId()).thenReturn(1L);
        when(campaignMockDTO_2.getId()).thenReturn(2L);
        when(campaignTransformer.transformToCampaignDTO(any(Campaign.class)))
                .thenReturn(campaignMockDTO_1, campaignMockDTO_2);

        // then
        List<CampaignDTO> allCampaigns = campaignService.getAllCampaigns();
        assertEquals(allCampaigns.size(), 2);
        assertEquals(allCampaigns.get(1).getId(), 2L);
    }

    @Test
    void deleteCampaign() {
        Long id = 1L;
        campaignService.deleteCampaign(id);
        verify(campaignRepository, times(1)).deleteById(eq(id));
    }
}