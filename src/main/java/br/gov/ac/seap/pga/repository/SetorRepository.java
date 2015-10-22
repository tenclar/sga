	package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Setor;

@Repository
public interface SetorRepository extends CrudRepository<Setor, Integer> {

	Setor findByDescription(String description);
	
	List<Setor> findAll(Sort sort);

	
}
