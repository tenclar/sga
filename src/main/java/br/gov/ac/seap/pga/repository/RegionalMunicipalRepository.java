package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.RegionalMunicipal;

@Repository
public interface RegionalMunicipalRepository extends CrudRepository<RegionalMunicipal, Integer> {


	RegionalMunicipal findById(Long arg0);	
	
	List<RegionalMunicipal> findByNome(String arg0);	
	List<RegionalMunicipal> findByNomeLike(String arg0);	
	List<RegionalMunicipal> findByDescricaoLike(String arg0);	
	List<RegionalMunicipal> findAll(Sort sort);
	
	
//	@Query("select u from RegionalMunicipal u where u.firstname = ?")
//	List<RegionalMunicipal> findByFirstname(String firstname);
	
//	@Query("select u from RegionalMunicipal u where u.firstname = :name or u.lastname = :name")
//	List<RegionalMunicipal> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
