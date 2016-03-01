package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Vegetacao;

@Repository
public interface VegetacaoRepository extends CrudRepository<Vegetacao, Integer> {


	Vegetacao findById(Long arg0);	
		
	List<Vegetacao> findByNameLike(String arg0);
	List<Vegetacao> findByDescricaoLike(String arg0);
	List<Vegetacao> findAll(Sort sort);
	
	
	
//	@Query("select u from Vegetacao u where u.firstname = ?")
//	List<Vegetacao> findByFirstname(String firstname);
	
//	@Query("select u from Vegetacao u where u.firstname = :name or u.lastname = :name")
//	List<Vegetacao> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
