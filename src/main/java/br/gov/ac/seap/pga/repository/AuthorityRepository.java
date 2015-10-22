package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer>{

	Authority findByAuthority(String authority);	
	Authority findById(Long arg1);
	
	List<Authority> findAll();
	
	List<Authority> findAll(Sort sort);
	
}
