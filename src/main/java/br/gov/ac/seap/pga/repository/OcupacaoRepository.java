package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Ocupacao;

@Repository
public interface OcupacaoRepository extends CrudRepository<Ocupacao, Integer> {


	Ocupacao findById(Long arg0);		
	List<Ocupacao> findByNameLike(String arg0);	
	List<Ocupacao> findAll(Sort sort);
	
	
	
//	@Query("select u from Ocupacao u where u.firstname = ?")
//	List<Ocupacao> findByFirstname(String firstname);
	
//	@Query("select u from Ocupacao u where u.firstname = :name or u.lastname = :name")
//	List<Ocupacao> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
