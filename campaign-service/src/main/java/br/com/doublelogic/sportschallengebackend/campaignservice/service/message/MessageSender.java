package br.com.doublelogic.sportschallengebackend.campaignservice.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * Componente responsável por enviar as mensagens para o ActiveMQ
 * 
 * @author Diego
 *
 */
@Component
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * Envia a mensagem para o ActiveMQ
	 * @param message uma mensagem de atualização ou de remoção
	 */
	public void sendMessage(Message message) {
		try {
			jmsTemplate.convertAndSend("campaign.queue", message.toJSON());
		} catch (Exception e) {
			log.error("could not send message", e);
		}
	}
	
}
