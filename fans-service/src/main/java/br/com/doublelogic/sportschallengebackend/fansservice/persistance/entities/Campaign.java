package br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Campaign {

	/**
	 * Identificação única da campanha
	 */
	@Id
	private Long id;
	
	/**
	 * Nome da campanha
	 */
	private String name;
	
	/**
	 * Identificação do time da campanha
	 */
	private Long idTeam;
	
	/**
	 * Data de início da campanha
	 */
	@Column(columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	
	/**
	 * Data de fim da campanha
	 */
	@Column(columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date finishDate;
	
}