package br.edu.infnet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.model.Batalha;

@Repository
public interface BatalhaRepository extends CrudRepository<Batalha, Long> {

}
