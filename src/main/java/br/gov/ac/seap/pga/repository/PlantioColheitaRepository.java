package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.PlantioColheita;

@Repository
public interface PlantioColheitaRepository extends CrudRepository<PlantioColheita, Long> {


	PlantioColheita findById(Long arg0);	
		
	List<PlantioColheita> findByProducaoIdOrderByIdDesc(Long arg0);	
	List<PlantioColheita> findAll(Sort sort);
	
	
	
	
	
}
