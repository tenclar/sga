package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Cidade;
import br.gov.ac.seap.pga.model.Estado;

@Repository
public interface CidadeRepository extends CrudRepository<Cidade, Integer> {


	Cidade findById(Long arg0);		
	List<Cidade> findByEstadoOrderByIdDesc(Estado arg0);	
	List<Cidade> findByNomeLike(String arg0);	
	List<Cidade> findAll(Sort sort);
	
	
//	@Query("select u from Cidade u where u.firstname = ?")
//	List<Cidade> findByFirstname(String firstname);
	
//	@Query("select u from Cidade u where u.firstname = :name or u.lastname = :name")
//	List<Cidade> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
