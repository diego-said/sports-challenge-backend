package br.com.doublelogic.sportschallengebackend.fansservice.service.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Campaign;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Fan;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories.FanCampaignRepository;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories.FanRepository;
import br.com.doublelogic.sportschallengebackend.fansservice.service.FanService;
import br.com.doublelogic.sportschallengebackend.fansservice.service.errors.FanNotFoundException;
import br.com.doublelogic.sportschallengebackend.fansservice.service.errors.FanSameEmailException;

@RestController
public class FanResource {

	@Autowired
	private FanRepository fanRepository;
	
	@Autowired
	private FanCampaignRepository fanCampaignRepository;
	
	@Autowired
	private FanService fanService;
	
	@GetMapping("/fans")
	public List<Fan> retrieveAllFans() {
		return fanRepository.findAll();
	}
	
	@GetMapping("/fans/{id}")
	public Fan retrieveFan(@PathVariable long id) {
		Optional<Fan> fan = fanRepository.findById(id);

		if (!fan.isPresent())
			throw new FanNotFoundException("id-" + id);

		return fan.get();
	}
	
	@DeleteMapping("/fans/{id}")
	public void deleteFan(@PathVariable long id) {
		Optional<Fan> fan = fanRepository.findById(id);

		if (!fan.isPresent())
			throw new FanNotFoundException("id-" + id);
		
		fanRepository.deleteById(id);
	}
	
	@PostMapping("/fans")
	public ResponseEntity<Object> createFan(@RequestBody Fan fan) {
		Optional<Fan> optionalFan = fanRepository.findByEmail(fan.getEmail());

		if (!optionalFan.isPresent()) {
			Fan savedFan = fanRepository.save(fan);

			fanService.requestAndSaveCampaigns(savedFan);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedFan.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		} else {
			List<Campaign> list = fanCampaignRepository.findCampaignsByFanId(optionalFan.get().getId());
			if(list.isEmpty()) {
				list = fanService.requestAndSaveCampaigns(optionalFan.get());
				ObjectMapper mapper = new ObjectMapper();
				String json = "";
				try {
					json = mapper.writeValueAsString(list);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return new ResponseEntity<Object>(json, HttpStatus.OK);
			} else {
				throw new FanSameEmailException(fan.getEmail());
			}
		}
	}
	
	@PutMapping("/fans/{id}")
	public ResponseEntity<Object> updateFan(@RequestBody Fan fan, @PathVariable long id) {

		Optional<Fan> fanOptional = fanRepository.findById(id);

		if (!fanOptional.isPresent())
			return ResponseEntity.notFound().build();

		fan.setId(id);
		
		fanRepository.save(fan);
		
		fanService.requestAndSaveCampaigns(fan);

		return ResponseEntity.noContent().build();
	}
	
}