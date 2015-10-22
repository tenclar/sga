package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.UnidadeMedida;

@Repository
public interface UnidadeMedidaRepository extends CrudRepository<UnidadeMedida, Integer> {


	UnidadeMedida findById(Long arg0);	
	UnidadeMedida findBySigla(String arg0);
	List<UnidadeMedida> findBySiglaOrderByIdDesc(String arg0);
	List<UnidadeMedida> findByNome(String arg0);	
	List<UnidadeMedida> findByNomeLike(String arg0);	
	List<UnidadeMedida> findAll(Sort sort);
	
	
	
//	@Query("select u from UnidadeMedida u where u.firstname = ?")
//	List<UnidadeMedida> findByFirstname(String firstname);
	
//	@Query("select u from UnidadeMedida u where u.firstname = :name or u.lastname = :name")
//	List<UnidadeMedida> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
