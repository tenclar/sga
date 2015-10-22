package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.CadeiaProdutiva;

@Repository
public interface CadeiaProdutivaRepository extends CrudRepository<CadeiaProdutiva, Integer> {


	CadeiaProdutiva findById(Long arg0);
	
	List<CadeiaProdutiva> findByName(String arg0);	
	List<CadeiaProdutiva> findByNameLike(String arg0);	
	List<CadeiaProdutiva> findAll(Sort sort);
	
	
	
//	@Query("select u from CadeiaProdutiva u where u.firstname = ?")
//	List<CadeiaProdutiva> findByFirstname(String firstname);
	
//	@Query("select u from CadeiaProdutiva u where u.firstname = :name or u.lastname = :name")
//	List<CadeiaProdutiva> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
