package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.RegionalEstadual;

@Repository
public interface RegionalEstadualRepository extends CrudRepository<RegionalEstadual, Integer> {


	RegionalEstadual findById(Long arg0);	
	
	List<RegionalEstadual> findByNome(String arg0);	
	List<RegionalEstadual> findByNomeLike(String arg0);	
	List<RegionalEstadual> findByDescricaoLike(String arg0);	
	List<RegionalEstadual> findAll(Sort sort);
	
	
//	@Query("select u from RegionalEstadual u where u.firstname = ?")
//	List<RegionalEstadual> findByFirstname(String firstname);
	
//	@Query("select u from RegionalEstadual u where u.firstname = :name or u.lastname = :name")
//	List<RegionalEstadual> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
