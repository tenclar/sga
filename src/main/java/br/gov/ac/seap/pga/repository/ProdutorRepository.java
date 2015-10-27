package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Produtor;


@Repository
public interface ProdutorRepository extends CrudRepository<Produtor, Integer> {

	Produtor findById(Long arg0);
	Produtor findByCpf(String arg0);
	Produtor findByName(String arg0);
	List<Produtor> findByCpfOrderByIdDesc(String arg0);
	List<Produtor> findByNameLike(String arg0);	
	List<Produtor> findAll(Sort sort);
	
	
//	@Query("select u from Produtor u where u.firstname = ?")
//	List<Produtor> findByFirstname(String firstname);
	
//	@Query("select u from Produtor u where u.firstname = :name or u.lastname = :name")
//	List<Produtor> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
