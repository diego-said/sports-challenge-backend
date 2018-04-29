package br.com.doublelogic.sportschallengebackend.campaignservice.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendMessage() {
		jmsTemplate.convertAndSend("campaign.queue", "testMessage");
	}
	
}
