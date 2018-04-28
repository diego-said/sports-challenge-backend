package br.com.doublelogic.sportschallengebackend.fansservice.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FanNotFoundException extends RuntimeException {

	public FanNotFoundException(String id) {
		super("could not find fan '" + id + "'.");
	}

}