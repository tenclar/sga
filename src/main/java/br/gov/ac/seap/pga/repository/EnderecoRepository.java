package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Bairro;
import br.gov.ac.seap.pga.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {


	Endereco findById(Long arg0);		
		
	List<Endereco> findByLogradouroLike(String arg0);	
	List<Endereco> findAll(Sort sort);
	
	@Query("select e from Endereco e where e.bairro.cidade.id = ?1 and e.logradouro like ?2")
	List<Endereco> findByCidadeIdAndLogradouroLike(Long arg0, String arg1,Pageable pageable);
	
	List<Endereco> findByBairroAndLogradouroLike(Bairro arg0, String arg1, Pageable pageable);
	
	
//	@Query("select u from Endereco u where u.firstname = ?")
//	List<Endereco> findByFirstname(String firstname);
	
//	@Query("select u from Endereco u where u.firstname = :name or u.lastname = :name")
//	List<Endereco> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
