package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Topografia;

@Repository
public interface TopografiaRepository extends CrudRepository<Topografia, Integer> {


	Topografia findById(Long arg0);	
		
	List<Topografia> findByNameLike(String arg0);
	List<Topografia> findByDescricaoLike(String arg0);
	List<Topografia> findAll(Sort sort);
	
	
	
//	@Query("select u from Topografia u where u.firstname = ?")
//	List<Topografia> findByFirstname(String firstname);
	
//	@Query("select u from Topografia u where u.firstname = :name or u.lastname = :name")
//	List<Topografia> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
