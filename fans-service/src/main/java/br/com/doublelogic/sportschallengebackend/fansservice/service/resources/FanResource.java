package br.com.doublelogic.sportschallengebackend.fansservice.service.resources;

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

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Fan;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories.FanRepository;
import br.com.doublelogic.sportschallengebackend.fansservice.service.errors.FanNotFoundException;

@RestController
public class FanResource {

	@Autowired
	private FanRepository fanRepository;
	
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
		Fan savedFan = fanRepository.save(fan);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedFan.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/fans/{id}")
	public ResponseEntity<Object> updateFan(@RequestBody Fan fan, @PathVariable long id) {

		Optional<Fan> fanOptional = fanRepository.findById(id);

		if (!fanOptional.isPresent())
			return ResponseEntity.notFound().build();

		fan.setId(id);
		
		fanRepository.save(fan);

		return ResponseEntity.noContent().build();
	}
	
}