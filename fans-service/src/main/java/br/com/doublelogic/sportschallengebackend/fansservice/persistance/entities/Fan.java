package br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 
 * Entidade que representa as informações de um fã do programa sócio torcedor
 * 
 * @author Diego
 *
 */
@Entity
@Data
public class Fan {

	/**
	 * Identificação única do fã
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Nome do fã
	 */
	private String name;
	
	/**
	 * Email do fã
	 */
	@Column(unique = true)
	private String email;
	
	/**
	 * Identificação única do time de coração do fã
	 */
	private Long idTeam;
	
	/**
	 * Data de nascimento do fã
	 */
	@Column(columnDefinition = "DATE")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
}