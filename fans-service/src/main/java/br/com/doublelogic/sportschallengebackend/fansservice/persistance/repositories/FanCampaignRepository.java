package br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Campaign;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.FanCampaign;

/**
 * 
 * Componente resposável por fornecer o acesso e atualização das informações salvas no banco para a entidade FanCampaign
 * 
 * @author Diego
 *
 */
@Repository
public interface FanCampaignRepository extends JpaRepository<FanCampaign, Long> {

	/**
	 * Busca todas as campanhas salvas de que o fã participa
	 * @param idFan identificação do fã
	 * @return uma lista com as informações das campanhas
	 */
	@Query("select c from FanCampaign fc inner join fc.campaign c where fc.fan.id = :idFan")
	List<Campaign> findCampaignsByFanId(@Param("idFan") Long idFan);
	
	/**
	 * Remove todas as relações com os fãs de uma campanha
	 * @param idCampaign identificação única da campanha
	 */
	@Transactional
	@Modifying
	@Query("delete from FanCampaign fc where fc.campaign.id = :idCampaign")
	void deleteByCampaignId(@Param("idCampaign") Long idCampaign);
	
}