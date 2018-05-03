package br.com.doublelogic.sportschallengebackend.fansservice.service.rest;

import java.util.List;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Campaign;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CampaignApi {

	  @GET("/campaigns/team/{id}")
	  Call<List<Campaign>> listTeamCapaigns(@Path("id") Long id);
	
}