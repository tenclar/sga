package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.PreparoArea;
import br.gov.ac.seap.pga.model.Producao;

@Repository
public interface PreparoAreaRepository extends CrudRepository<PreparoArea, Integer> {


	PreparoArea findById(Long arg0);	
		
	List<PreparoArea> findByProducaoOrderByIdDesc(Producao arg0);	
	List<PreparoArea> findAll(Sort sort);
	
	
	
//	@Query("select u from PreparoArea u where u.firstname = ?")
//	List<PreparoArea> findByFirstname(String firstname);
	
//	@Query("select u from PreparoArea u where u.firstname = :name or u.lastname = :name")
//	List<PreparoArea> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
