package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Bairro;
import br.gov.ac.seap.pga.model.Cidade;

@Repository
public interface BairroRepository extends CrudRepository<Bairro, Integer> {


	Bairro findById(Long arg0);		
	List<Bairro> findByCidadeOrderByIdDesc(Cidade arg0);	
	List<Bairro> findByNomeLike(String arg0);	
	List<Bairro> findAll(Sort sort);
	
	
//	@Query("select u from Bairro u where u.firstname = ?")
//	List<Bairro> findByFirstname(String firstname);
	
//	@Query("select u from Bairro u where u.firstname = :name or u.lastname = :name")
//	List<Bairro> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
