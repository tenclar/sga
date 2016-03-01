package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.InsumoTrato;
import br.gov.ac.seap.pga.model.Producao;

@Repository
public interface InsumoTratoRepository extends CrudRepository<InsumoTrato, Integer> {


	InsumoTrato findById(Long arg0);	
		
	List<InsumoTrato> findByProducaoOrderByIdDesc(Producao arg0);	
	List<InsumoTrato> findAll(Sort sort);
	
	
	
//	@Query("select u from InsumoTrato u where u.firstname = ?")
//	List<InsumoTrato> findByFirstname(String firstname);
	
//	@Query("select u from InsumoTrato u where u.firstname = :name or u.lastname = :name")
//	List<InsumoTrato> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
