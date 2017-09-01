package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.ProducaoFomento;


@Repository
public interface ProducaoFomentoRepository extends CrudRepository<ProducaoFomento, Long> {


	ProducaoFomento findById(Long arg0);	
		
	List<ProducaoFomento> findByProducaoIdOrderByIdDesc(Long arg0);	
	List<ProducaoFomento> findAll(Sort sort);
	
	
	
//	@Query("select u from ProducaoFomento u where u.firstname = ?")
//	List<ProducaoFomento> findByFirstname(String firstname);
	
//	@Query("select u from ProducaoFomento u where u.firstname = :name or u.lastname = :name")
//	List<ProducaoFomento> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
