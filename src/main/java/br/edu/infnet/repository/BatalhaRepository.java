package br.edu.infnet.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.model.Battle;

import javax.transaction.Transactional;

@Repository
public interface BatalhaRepository extends CrudRepository<Battle, Long> {
	
	@Modifying
    @Transactional
    @Query(value = "insert into battle (id_hero, id_monster, first_player, date_time) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void saveBattle(Integer idHero, Integer idMonster, String firstPlayer, String dateNow);

    @Modifying
    @Transactional
    @Query(value = "insert into attack (id_hero, id_monster, pdv_hero, pdv_monster, id_battle, date_time) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void saveAttack(Integer idHero, Integer idMonster, Integer pdvHero, Integer pdvMonster, Integer idBattle, String dateNow);

    @Transactional
    @Query(value = "SELECT MAX(id) as id FROM battle", nativeQuery = true)
    public Long lastIdBattle();

}
