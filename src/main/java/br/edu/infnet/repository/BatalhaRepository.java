package br.edu.infnet.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.model.Start;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface BatalhaRepository extends CrudRepository<Start, Long> {
	
	@Modifying
    @Transactional
    @Query(value = "insert into start (id_hero, id_monster, first_player, date_time) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void saveStart(Integer idHero, Integer idMonster, String firstPlayer, String dateNow);

    @Modifying
    @Transactional
    @Query(value = "insert into attack (id_hero, id_monster, pdv_hero, pdv_monster, date_time) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void saveAttack(Integer idHero, Integer idMonster, Integer pdvHero, Integer pdvMonster, String dateNow);
}
