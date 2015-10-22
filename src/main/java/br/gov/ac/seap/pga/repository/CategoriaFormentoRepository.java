	package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.CategoriaFormento;

@Repository
public interface CategoriaFormentoRepository extends CrudRepository<CategoriaFormento, Integer> {

	CategoriaFormento findById(Long arg0);	
	
	List<CategoriaFormento> findByDescriptionLikeOrderByIdDesc(String arg0);		
	List<CategoriaFormento> findByNomeLikeOrderByIdDesc(String arg0);		
	List<CategoriaFormento> findAll(Sort sort);

	
}
