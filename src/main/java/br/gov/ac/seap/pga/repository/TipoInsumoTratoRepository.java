package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.TipoInsumoTrato;

@Repository
public interface TipoInsumoTratoRepository extends CrudRepository<TipoInsumoTrato, Integer> {


	TipoInsumoTrato findById(Long arg0);	
		
	List<TipoInsumoTrato> findByNameLike(String arg0);
	List<TipoInsumoTrato> findByDescricaoLike(String arg0);
	List<TipoInsumoTrato> findAll(Sort sort);
	
	
	
//	@Query("select u from TipoInsumoTrato u where u.firstname = ?")
//	List<TipoInsumoTrato> findByFirstname(String firstname);
	
//	@Query("select u from TipoInsumoTrato u where u.firstname = :name or u.lastname = :name")
//	List<TipoInsumoTrato> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
