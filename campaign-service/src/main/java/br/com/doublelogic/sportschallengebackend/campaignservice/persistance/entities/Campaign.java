package br.com.doublelogic.sportschallengebackend.campaignservice.persistance.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Campaign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Long idTeam;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date finishDate;
	
}
