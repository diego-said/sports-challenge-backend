package br.com.doublelogic.sportschallengebackend.fansservice.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories.CampaignRepository;
import br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories.FanCampaignRepository;

/**
 * 
 * Componente responsável por receber as mensagens do ActiveMQ
 * 
 * @author Diego
 *
 */
@Component
public class MessageReceiver {

	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private FanCampaignRepository fanCampaignRepository;
	
	/**
	 * Recebe o JSON da mensagem e atualiza as informações do banco de dados com base nas informações da mensagem
	 * @param json JSON da mensagem
	 */
	@JmsListener(destination = "campaign.queue")
	public void receive(String json) {
		Message receivedMessage = Message.fromJSON(json);
		if(receivedMessage != null) {
			if(receivedMessage.getMessageType() == MessageType.UPDATE_CAMPAIGN) {
				campaignRepository.save(receivedMessage.getUpdatedCampaign());
			} else if(receivedMessage.getMessageType() == MessageType.DELETE_CAMPAIGN) {
				fanCampaignRepository.deleteByCampaignId(receivedMessage.getCampaign().getId());
				fanCampaignRepository.flush();
				campaignRepository.deleteById(receivedMessage.getCampaign().getId());
				campaignRepository.flush();
			}
		}
	}
	
}