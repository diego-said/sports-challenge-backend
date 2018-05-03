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

@Repository
public interface FanCampaignRepository extends JpaRepository<FanCampaign, Long> {

	@Query("select c from FanCampaign fc inner join fc.campaign c where fc.fan.id = :idFan")
	List<Campaign> findCampaignsByFanId(@Param("idFan") Long idFan);
	
	@Transactional
	@Modifying
	@Query("delete from FanCampaign fc where fc.campaign.id = :idCampaign")
	void deleteByCampaignId(@Param("idCampaign") Long idCampaign);
	
}