package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Colheita;
import br.gov.ac.seap.pga.model.Producao;

@Repository
public interface ColheitaRepository extends CrudRepository<Colheita, Integer> {


	Colheita findById(Long arg0);	
		
	List<Colheita> findByProducaoOrderByIdDesc(Producao arg0);	
	List<Colheita> findAll(Sort sort);
	
	
	
//	@Query("select u from Colheita u where u.firstname = ?")
//	List<Colheita> findByFirstname(String firstname);
	
//	@Query("select u from Colheita u where u.firstname = :name or u.lastname = :name")
//	List<Colheita> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
