package br.com.doublelogic.sportschallengebackend.campaignservice.business;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.doublelogic.sportschallengebackend.campaignservice.persistance.entities.Campaign;
import br.com.doublelogic.sportschallengebackend.campaignservice.persistance.repositories.CampaignRepository;

/**
 * 
 * Componente responsável por operações executadas nas campanhas
 * 
 * @author Diego
 *
 */
@Component
public class CampaignManager {

	@Autowired
	private CampaignRepository campaignRepository;

	/**
	 * Formatador para só trabalhar com a parte da data, ignorando o tempo
	 */
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	/**
	 * Busca todas as campanhas válidas e com mesma vigencia da campanha informada. 
	 * Para cada campanha encontrada é adicionado 1 dia ao seu término. Todas as campanhas encontradas são atualizadas
	 * e a campanha informada é salva na base de dados.
	 * @param campaign campanha a ser cadastrada na base de dados
	 * @return campanha informada com o seu id único preenchido
	 */
	public Campaign adjustAndSave(Campaign campaign) {
		HashMap<String, Campaign> campaigns = new HashMap<>();
		campaigns.put(sdf.format(campaign.getFinishDate()), campaign);
		
		findAllCampaigns(campaign).forEach(c -> {
			do {
				c.setFinishDate(addDay(c.getFinishDate()));
			} while(campaigns.containsKey(sdf.format(c.getFinishDate())));
			campaigns.put(sdf.format(c.getFinishDate()), c);
		});
		
		campaignRepository.saveAll(campaigns.values());
		campaignRepository.flush();
		
		return campaign;
	}
	
	/**
	 * Busca todas as campanhas da base que estejam no mesmo período de vigencia da campanha informada
	 * @param campaign campanha a ser verificada
	 * @return uma lista com as campanhas válidas e com mesma vigencia da campanha informada
	 */
	private List<Campaign> findAllCampaigns(Campaign campaign) {
		return campaignRepository.findAll(campaign.getStartDate(), campaign.getFinishDate());
	}
	
	/**
	 * Adiciona 1 dia a mais data com relação a data informada
	 * @param date data para ser somado 1 dia
	 * @return nova data considerando a soma de 1 dia
	 */
	private Date addDay(Date date) {
		return Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
	}
	
}