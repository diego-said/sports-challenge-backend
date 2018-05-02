package br.com.doublelogic.sportschallengebackend.campaignservice.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * Erro de execução para quando campanha não for encontrada
 * 
 * @author Diego
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CampaignNotFoundException extends RuntimeException {

	public CampaignNotFoundException(String id) {
		super("could not find campaign '" + id + "'.");
	}
	
}