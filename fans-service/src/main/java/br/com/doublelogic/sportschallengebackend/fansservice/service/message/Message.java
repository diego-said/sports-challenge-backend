package br.com.doublelogic.sportschallengebackend.fansservice.service.message;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Campaign;
import lombok.Data;

/**
 * 
 * Componente que represente as informações das mensagens enviadas ao AcitveMQ
 * 
 * @author Diego
 *
 */
@Data
public class Message {

	/**
	 * Tipo de mensagem que será enviada
	 */
	private MessageType messageType;
	
	/**
	 * Representa as informações da campanha, tanto a campanha removida
	 * quanto as informações antigas de uma campanha atualizada
	 */
	private Campaign campaign;
	
	/**
	 * Representa as novas informações de uma campanha atualizada
	 */
	private Campaign updatedCampaign;
	
	
	public static Message fromJSON(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Message.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}