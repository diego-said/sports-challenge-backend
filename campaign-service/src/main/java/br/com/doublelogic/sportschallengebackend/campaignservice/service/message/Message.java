package br.com.doublelogic.sportschallengebackend.campaignservice.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.doublelogic.sportschallengebackend.campaignservice.persistance.entities.Campaign;
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
	
	private Message() {
	}
	
	public static class Factory {
		public static Message createDeleteMessage(Campaign deletedCampaign) {
			Message deleteMessage = new Message();
			deleteMessage.setMessageType(MessageType.DELETE_CAMPAIGN);
			deleteMessage.setCampaign(deletedCampaign);
			return deleteMessage;
		}
		
		public static Message createUpdateMessage(Campaign campaign, Campaign updatedCampaign) {
			Message updateMessage = new Message();
			updateMessage.setMessageType(MessageType.UPDATE_CAMPAIGN);
			updateMessage.setCampaign(campaign);
			updateMessage.setUpdatedCampaign(updatedCampaign);
			return updateMessage;
		}
	}
	
	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}