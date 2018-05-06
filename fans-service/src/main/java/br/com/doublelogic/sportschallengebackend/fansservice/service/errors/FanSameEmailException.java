package br.com.doublelogic.sportschallengebackend.fansservice.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * Erro de execução para quando o email do fã já ter sido utilizado
 * 
 * @author Diego
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.FORBIDDEN)
public class FanSameEmailException extends RuntimeException {

	public FanSameEmailException(String email) {
		super("fan found with the same email '" + email + "'.");
	}
	
}