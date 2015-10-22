package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.CategoriaMaterial;

@Repository
public interface CategoriaMaterialRepository extends CrudRepository<CategoriaMaterial, Integer> {


	CategoriaMaterial findById(Long arg0);	
		
	List<CategoriaMaterial> findByNomeLike(String arg0);
	List<CategoriaMaterial> findByDescriptionLike(String arg0);
	List<CategoriaMaterial> findAll(Sort sort);
	
	
	
//	@Query("select u from CategoriaMaterial u where u.firstname = ?")
//	List<CategoriaMaterial> findByFirstname(String firstname);
	
//	@Query("select u from CategoriaMaterial u where u.firstname = :name or u.lastname = :name")
//	List<CategoriaMaterial> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
