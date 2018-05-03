package br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Fan;

@Repository
public interface FanRepository extends JpaRepository<Fan, Long> {

	@Query("select f from Fan f where f.email = :email")
	Optional<Fan> findByEmail(@Param("email") String email);
	
}