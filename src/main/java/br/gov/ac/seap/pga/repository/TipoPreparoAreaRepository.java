package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.TipoPreparoArea;

@Repository
public interface TipoPreparoAreaRepository extends CrudRepository<TipoPreparoArea, Integer> {


	TipoPreparoArea findById(Long arg0);	
		
	List<TipoPreparoArea> findByNameLike(String arg0);
	List<TipoPreparoArea> findByDescricaoLike(String arg0);
	List<TipoPreparoArea> findAll(Sort sort);
	
	
	
//	@Query("select u from TipoPreparoArea u where u.firstname = ?")
//	List<TipoPreparoArea> findByFirstname(String firstname);
	
//	@Query("select u from TipoPreparoArea u where u.firstname = :name or u.lastname = :name")
//	List<TipoPreparoArea> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
