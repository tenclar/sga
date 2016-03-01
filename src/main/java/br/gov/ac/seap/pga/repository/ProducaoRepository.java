package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Producao;

@Repository
public interface ProducaoRepository extends CrudRepository<Producao, Integer> {


	Producao findById(Long arg0);
		
	
	List<Producao> findByDescricaoLike(String arg0);
	List<Producao> findAll(Sort sort);
	
	
	
//	@Query("select u from Producao u where u.firstname = ?")
//	List<Producao> findByFirstname(String firstname);
	
//	@Query("select u from Producao u where u.firstname = :name or u.lastname = :name")
//	List<Producao> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
