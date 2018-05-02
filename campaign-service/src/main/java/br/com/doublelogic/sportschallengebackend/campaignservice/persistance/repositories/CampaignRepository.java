package br.com.doublelogic.sportschallengebackend.campaignservice.persistance.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.doublelogic.sportschallengebackend.campaignservice.persistance.entities.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
	
	@Query("select c from Campaign c where c.finishDate >= CURRENT_DATE()")
	List<Campaign> findAllValid();
	
	@Query("select c from Campaign c where c.idTeam = :idTeam")
	List<Campaign> findByIdTeam(@Param("idTeam") Long idTeam);
	
	@Query("select c from Campaign c where c.finishDate >= CURRENT_DATE() and c.finishDate between :startDate and :finishDate order by c.finishDate")
	List<Campaign> findAll(@Param("startDate") Date startDate, @Param("finishDate") Date finishDate);
	
}