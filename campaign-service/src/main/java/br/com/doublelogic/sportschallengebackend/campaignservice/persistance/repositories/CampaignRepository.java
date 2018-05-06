package br.com.doublelogic.sportschallengebackend.campaignservice.persistance.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.doublelogic.sportschallengebackend.campaignservice.persistance.entities.Campaign;

/**
 * 
 * Componente resposável por fornecer o acesso e atualização das informações salvas no banco para a entidade Campaign
 * 
 * @author Diego
 *
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
	
	/**
	 * Busca todas as campanhas que estejam válidas
	 * @return uma lista com as informações das campanhas
	 */
	@Query("select c from Campaign c where c.finishDate >= CURRENT_DATE()")
	List<Campaign> findAllValid();
	
	/**
	 * Busca todas as campanhas válidas de um determinado time
	 * @param idTeam id do time
	 * @return uma lista com as informações das campanhas
	 */
	@Query("select c from Campaign c where c.finishDate >= CURRENT_DATE() and c.idTeam = :idTeam")
	List<Campaign> findByIdTeam(@Param("idTeam") Long idTeam);
	
	/**
	 * Busca todas as campanhas que estejam válidas e dentro do período informado
	 * @param startDate data de início do período
	 * @param finishDate data de fim do período
	 * @return uma lista com as informações das campanhas
	 */
	@Query("select c from Campaign c where c.finishDate >= CURRENT_DATE() and c.finishDate between :startDate and :finishDate order by c.finishDate")
	List<Campaign> findAll(@Param("startDate") Date startDate, @Param("finishDate") Date finishDate);
	
}