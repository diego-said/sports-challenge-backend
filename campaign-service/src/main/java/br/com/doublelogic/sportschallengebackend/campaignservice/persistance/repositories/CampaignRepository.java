package br.com.doublelogic.sportschallengebackend.campaignservice.persistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doublelogic.sportschallengebackend.campaignservice.persistance.entities.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
	
}
