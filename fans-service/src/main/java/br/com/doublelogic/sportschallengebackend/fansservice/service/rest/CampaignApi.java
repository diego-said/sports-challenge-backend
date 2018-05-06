package br.com.doublelogic.sportschallengebackend.fansservice.service.rest;

import java.util.List;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Campaign;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 
 * Interface responsável por mapear a API Rest do serviço de campanhas
 * 
 * @author Diego
 *
 */
public interface CampaignApi {

	/**
	 * Solicita todas as campanhas de um determinado time
	 * @param id identificação única do time
	 * @return lista com as informações das campanhas
	 */
	@GET("/campaigns/team/{id}")
	Call<List<Campaign>> listTeamCapaigns(@Path("id") Long id);

}