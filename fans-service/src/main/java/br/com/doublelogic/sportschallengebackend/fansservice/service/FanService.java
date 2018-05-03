package br.com.doublelogic.sportschallengebackend.fansservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Campaign;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Fan;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.FanCampaign;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories.CampaignRepository;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories.FanCampaignRepository;
import br.com.doublelogic.sportschallengebackend.fansservice.service.rest.CampaignRestService;

@Service
public class FanService {

	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private FanCampaignRepository fanCampaignRepository;
	
	@Autowired
	private CampaignRestService campaignHttpService;

	public List<Campaign> requestAndSaveCampaigns(Fan fan) {
		List<Campaign> campaigns = campaignHttpService.listTeamCampaigns(fan.getIdTeam());
		if(!campaigns.isEmpty()) {
			List<FanCampaign> list = new ArrayList<>(campaigns.size());
			campaigns.forEach(c -> {
				FanCampaign fanCampaign = new FanCampaign();
				fanCampaign.setFan(fan);
				fanCampaign.setCampaign(c);
				list.add(fanCampaign);
			});
			campaignRepository.saveAll(campaigns);
			campaignRepository.flush();
			fanCampaignRepository.saveAll(list);
			fanCampaignRepository.flush();
		}
		return campaigns;
	}
	
}