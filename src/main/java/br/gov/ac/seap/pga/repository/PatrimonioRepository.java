package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Patrimonio;
import br.gov.ac.seap.pga.model.Material;

@Repository
public interface PatrimonioRepository extends CrudRepository<Patrimonio, Integer> {


	Patrimonio findById(Long arg0);	
	List<Patrimonio> findByDescricaoLike(String arg0);
	List<Patrimonio> findByMaterial(Material arg0);
	List<Patrimonio> findBytombamentoLike(String arg0);		
	List<Patrimonio> findByNameLike(String arg0);	
	List<Patrimonio> findAll(Sort sort);
	
	
	
//	@Query("select u from Patrimonio u where u.firstname = ?")
//	List<Patrimonio> findByFirstname(String firstname);
	
//	@Query("select u from Patrimonio u where u.firstname = :name or u.lastname = :name")
//	List<Patrimonio> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
