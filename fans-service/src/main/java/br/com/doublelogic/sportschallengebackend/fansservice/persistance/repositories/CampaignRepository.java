package br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Campaign;

/**
 * 
 * Componente resposável por fornecer o acesso e atualização das informações salvas no banco para a entidade Campaign
 * 
 * @author Diego
 *
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
	
}
