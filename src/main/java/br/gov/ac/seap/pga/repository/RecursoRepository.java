package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Recurso;

@Repository
public interface RecursoRepository extends CrudRepository<Recurso, Integer> {


	Recurso findById(Long arg0);	
	
	List<Recurso> findByDescriptionLike(String arg0);		
	List<Recurso> findByNomeLike(String arg0);	
	List<Recurso> findAll(Sort sort);
	
	
	
//	@Query("select u from Recurso u where u.firstname = ?")
//	List<Recurso> findByFirstname(String firstname);
	
//	@Query("select u from Recurso u where u.firstname = :name or u.lastname = :name")
//	List<Recurso> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
