package com.clickaway.controller;

import com.clickaway.entity.Campaign;
import com.clickaway.service.CampaignService;
import com.clickaway.service.dto.CampaignDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/campaign")
@Api(value = "Rest Controller for Campaign")
public class CampaignController {
    private final CampaignService campaignService;

    @GetMapping
    @ApiOperation(value = "Get All Campaigns")
    public ResponseEntity<List<CampaignDTO>> getAll() {
        return ResponseEntity.ok().body(campaignService.getAllCampaigns());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get One Campaign")
    public ResponseEntity<CampaignDTO> getOne(@PathVariable long id) {
        Optional<CampaignDTO> campaign = campaignService.getCampaign(id);
        return campaign.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Create New Campaigns", notes = "Notice that Transactional Method")
    public ResponseEntity<List<CampaignDTO>> addCampaigns(@RequestBody List<Campaign> campaigns) {
        List<CampaignDTO> campaigns_created = campaignService.addCampaign(campaigns);
        return ResponseEntity.ok(campaigns_created);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete One Campaign")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.ok().build();
    }

}
