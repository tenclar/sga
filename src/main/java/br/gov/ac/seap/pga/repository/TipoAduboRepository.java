package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.TipoAdubo;

@Repository
public interface TipoAduboRepository extends CrudRepository<TipoAdubo, Integer> {


	TipoAdubo findById(Long arg0);	
		
	List<TipoAdubo> findByNameLike(String arg0);
	List<TipoAdubo> findByDescricaoLike(String arg0);
	List<TipoAdubo> findAll(Sort sort);
	
	
	
//	@Query("select u from TipoAdubo u where u.firstname = ?")
//	List<TipoAdubo> findByFirstname(String firstname);
	
//	@Query("select u from TipoAdubo u where u.firstname = :name or u.lastname = :name")
//	List<TipoAdubo> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
