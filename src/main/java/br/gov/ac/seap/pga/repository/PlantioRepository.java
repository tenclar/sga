package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.AnaliseSolo;
import br.gov.ac.seap.pga.model.Plantio;
import br.gov.ac.seap.pga.model.Producao;

@Repository
public interface PlantioRepository extends CrudRepository<Plantio, Integer> {


	Plantio findById(Long arg0);	
		
	List<Plantio> findByProducaoOrderByIdDesc(Producao arg0);	
	List<Plantio> findAll(Sort sort);
	
	
	
//	@Query("select u from AnaliseSolo u where u.firstname = ?")
//	List<AnaliseSolo> findByFirstname(String firstname);
	
//	@Query("select u from AnaliseSolo u where u.firstname = :name or u.lastname = :name")
//	List<AnaliseSolo> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
