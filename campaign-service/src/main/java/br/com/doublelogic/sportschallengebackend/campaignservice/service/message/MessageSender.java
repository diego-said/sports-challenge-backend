package br.com.doublelogic.sportschallengebackend.campaignservice.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public void sendMessage(Message message) {
		try {
			jmsTemplate.convertAndSend("campaign.queue", message.toJSON());
		} catch (Exception e) {
			log.error("could not send message", e);
		}
	}
	
}
