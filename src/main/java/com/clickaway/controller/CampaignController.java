package com.clickaway.controller;

import com.clickaway.model.entity.Campaign;
import com.clickaway.service.CampaignServiceImpl;
import com.clickaway.service.dto.CampaignDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/campaign")
public class CampaignController {

    //@Autowired
    private final CampaignServiceImpl campaignService;

    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAll() {
        return ResponseEntity.ok().body(campaignService.getAllCampaigns());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getOne(@PathVariable long id) {
        CampaignDTO campaignDTO = campaignService.getCampaign(id);
        return (campaignDTO != null) ? ResponseEntity.ok().body(campaignDTO)
                                     : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CampaignDTO> addOne(@RequestBody Campaign campaign) {
        CampaignDTO campaignDTOCreated = campaignService.addCampaign(campaign);
        return ResponseEntity.ok(campaignDTOCreated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.ok().build();
    }

}
