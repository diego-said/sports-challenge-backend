package br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

/**
 * 
 * Entidade que representa a relação fã x campanha
 * 
 * @author Diego
 *
 */
@Entity
@Data
public class FanCampaign {

	/**
	 * Identificação única da relação
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Informações do fã
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="fanid", referencedColumnName="id")
	private Fan fan;

	/**
	 * Informações da campanha
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="campaignid", referencedColumnName="id")
	private Campaign campaign;
	
}