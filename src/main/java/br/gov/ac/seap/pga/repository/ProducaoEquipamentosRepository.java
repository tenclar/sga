package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.ProducaoEquipamentos;
import br.gov.ac.seap.pga.model.Producao;

@Repository
public interface ProducaoEquipamentosRepository extends CrudRepository<ProducaoEquipamentos, Long> {


	ProducaoEquipamentos findById(Long arg0);	
		
	List<ProducaoEquipamentos> findByProducaoIdOrderByIdDesc(Long arg0);	
	List<ProducaoEquipamentos> findAll(Sort sort);
	
	
	
//	@Query("select u from ProducaoEquipamentos u where u.firstname = ?")
//	List<ProducaoEquipamentos> findByFirstname(String firstname);
	
//	@Query("select u from ProducaoEquipamentos u where u.firstname = :name or u.lastname = :name")
//	List<ProducaoEquipamentos> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
