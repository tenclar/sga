package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Material;

@Repository
public interface MaterialRepository extends CrudRepository<Material, Integer> {


	Material findById(Long arg0);	
		
	List<Material> findByNameLike(String arg0);
	List<Material> findByDescricaoLike(String arg0);
	List<Material> findAll(Sort sort);
	
	
	
//	@Query("select u from Material u where u.firstname = ?")
//	List<Material> findByFirstname(String firstname);
	
//	@Query("select u from Material u where u.firstname = :name or u.lastname = :name")
//	List<Material> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
