package br.com.doublelogic.sportschallengebackend.fansservice.persistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doublelogic.sportschallengebackend.fansservice.persistance.entities.Fan;

@Repository
public interface FanRepository extends JpaRepository<Fan, Long> {

}
