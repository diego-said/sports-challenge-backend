package br.com.doublelogic.sportschallengebackend.fansservice.service.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Campaign;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * 
 * Componente responsável por fazer a requisição a API Rest do serviço de campanhas
 * 
 * @author Diego
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CampaignRestService {

	//TODO deveria vir do arquivo de config
	private String campaignApiBaseUrl = "http://localhost:8080/";
	
	private final Logger log;
	
	private final OkHttpClient httpClient;
	
	private final Retrofit retrofit;
	
	private final CampaignApi api;

	public CampaignRestService() {
		log = LoggerFactory.getLogger(getClass());
		
		httpClient = new OkHttpClient.Builder()
			    .connectTimeout(20, TimeUnit.SECONDS)
			    .writeTimeout(20, TimeUnit.SECONDS)
			    .readTimeout(30, TimeUnit.SECONDS)
			    .build();
		
		retrofit = new Retrofit.Builder()
			    .baseUrl(campaignApiBaseUrl)
			    .client(httpClient)
			    .addConverterFactory(JacksonConverterFactory.create())
			    .build();
		
		api = retrofit.create(CampaignApi.class);
	}

	public List<Campaign> listTeamCampaigns(Long idTeam) {
		try {
			Response<List<Campaign>> response = api.listTeamCapaigns(idTeam).execute();
			if(response.isSuccessful())
				return response.body();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return Collections.emptyList();
	}
	
}