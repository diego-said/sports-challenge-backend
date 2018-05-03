package br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Fan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private Long idTeam;
	
	@Column(columnDefinition = "DATE")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
}