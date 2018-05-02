package br.com.doublelogic.sportschallengebackend.campaignservice.service.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.doublelogic.sportschallengebackend.campaignservice.business.CampaignManager;
import br.com.doublelogic.sportschallengebackend.campaignservice.persistance.entities.Campaign;
import br.com.doublelogic.sportschallengebackend.campaignservice.persistance.repositories.CampaignRepository;
import br.com.doublelogic.sportschallengebackend.campaignservice.service.errors.CampaignNotFoundException;
import br.com.doublelogic.sportschallengebackend.campaignservice.service.message.Message;
import br.com.doublelogic.sportschallengebackend.campaignservice.service.message.MessageSender;

@RestController
public class CampaignResource {

	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private CampaignManager campaignManager;
	
	@GetMapping("/campaigns")
	public List<Campaign> retrieveAllCampaigns() {
		return campaignRepository.findAllValid();
	}
	
	@GetMapping("/campaigns/team/{id}")
	public List<Campaign> retrieveAllTeamCampaigns(@PathVariable long id) {
		return campaignRepository.findByIdTeam(id);
	}
	
	@GetMapping("/campaigns/{id}")
	public Campaign retrieveCampaign(@PathVariable long id) {
		Optional<Campaign> campaign = campaignRepository.findById(id);

		if (!campaign.isPresent())
			throw new CampaignNotFoundException("id-" + id);
		
		return campaign.get();
	}
	
	@DeleteMapping("/campaigns/{id}")
	public void deleteCampaign(@PathVariable long id) {
		Optional<Campaign> campaign = campaignRepository.findById(id);

		if (!campaign.isPresent())
			throw new CampaignNotFoundException("id-" + id);
		
		campaignRepository.deleteById(id);
		
		messageSender.sendMessage(Message.Factory.createDeleteMessage(campaign.get()));
	}
	
	@PostMapping("/campaigns")
	public ResponseEntity<Object> createCampaign(@RequestBody Campaign campaign) {
		Campaign savedCampaign = campaignManager.adjustAndSave(campaign);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCampaign.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/campaigns/{id}")
	public ResponseEntity<Object> updateCampaign(@RequestBody Campaign campaign, @PathVariable long id) {

		Optional<Campaign> campaignOptional = campaignRepository.findById(id);

		if (!campaignOptional.isPresent())
			return ResponseEntity.notFound().build();

		campaign.setId(id);
		
		campaignRepository.save(campaign);

		messageSender.sendMessage(Message.Factory.createUpdateMessage(campaignOptional.get(), campaign));
		
		return ResponseEntity.noContent().build();
	}
	
}