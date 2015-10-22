package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Estado;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {


	Estado findById(Long arg0);	
	Estado findBySigla(String arg0);
	List<Estado> findBySiglaOrderByIdDesc(String arg0);
	List<Estado> findByNome(String arg0);	
	List<Estado> findByNomeLike(String arg0);	
	List<Estado> findAll(Sort sort);
	
	
	
//	@Query("select u from Estado u where u.firstname = ?")
//	List<Estado> findByFirstname(String firstname);
	
//	@Query("select u from Estado u where u.firstname = :name or u.lastname = :name")
//	List<Estado> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
