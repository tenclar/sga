package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.CadeiaLeite;
import br.gov.ac.seap.pga.model.Producao;

@Repository
public interface CadeiaLeiteRepository extends CrudRepository<CadeiaLeite, Long> {


	CadeiaLeite findById(Long arg0);	
	
	
	List<CadeiaLeite> findAll(Sort sort);
	
	
	@Query("select p from CadeiaLeite p order by p.id desc")
	List<CadeiaLeite> findAllByQuery();


	List<CadeiaLeite> findByProducaoIdOrderByIdDesc(Long id);

		
		
	
}
