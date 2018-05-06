package br.com.doublelogic.sportschallengebackend.campaignservice.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private Logger log = LoggerFactory.getLogger(getClass());
	
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
	
	/**
	 * 
	 * Factory para a criação das mensagens de remoção e atualização de uma campanha
	 * 
	 * @author Diego
	 *
	 */
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
	
	/**
	 * Converte a mensagem para JSON para ser enviada ao ActiveMQ
	 * @return uma String com o JSON da mensagem
	 */
	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}
}