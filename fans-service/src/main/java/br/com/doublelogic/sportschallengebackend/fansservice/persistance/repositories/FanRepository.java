package br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Fan;

/**
 * 
 * Componente resposável por fornecer o acesso e atualização das informações salvas no banco para a entidade Fan
 * 
 * @author Diego
 *
 */
@Repository
public interface FanRepository extends JpaRepository<Fan, Long> {

	/**
	 * Busca as informações de um fã utilizando o email informado
	 * @param email email do fã
	 * @return retorna as informações caso encontrado
	 */
	@Query("select f from Fan f where f.email = :email")
	Optional<Fan> findByEmail(@Param("email") String email);
	
}