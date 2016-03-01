package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.MaoDeObra;

@Repository
public interface MaoDeObraRepository extends CrudRepository<MaoDeObra, Integer> {


	MaoDeObra findById(Long arg0);	
		
	List<MaoDeObra> findByNameLike(String arg0);
	List<MaoDeObra> findByDescricaoLike(String arg0);
	List<MaoDeObra> findAll(Sort sort);
	
	
	
//	@Query("select u from MaoDeObra u where u.firstname = ?")
//	List<MaoDeObra> findByFirstname(String firstname);
	
//	@Query("select u from MaoDeObra u where u.firstname = :name or u.lastname = :name")
//	List<MaoDeObra> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
