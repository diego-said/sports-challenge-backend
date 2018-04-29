package br.com.doublelogic.sportschallengebackend.fansservice.service.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	@JmsListener(destination = "campaign.queue")
	public void receive(String message) {
		System.out.println("received message='"+message+"'");
	}
	
}