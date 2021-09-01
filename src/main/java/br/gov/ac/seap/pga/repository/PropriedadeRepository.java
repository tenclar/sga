package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Propriedade;

@Repository
public interface PropriedadeRepository extends CrudRepository<Propriedade, Long> {


	Propriedade findById(Long arg0);	
	List<Propriedade> findByNome(String arg0);	
	List<Propriedade> findByNomeLike(String arg0);	
	List<Propriedade> findAll(Sort sort);
	@Query("select u from Propriedade u fetch all properties")
	List<Propriedade> findAllPropriedade();
	List<Propriedade> findByProdutorNameLike(String arg0);
	List<Propriedade> findByProdutorId(Long arg0);
	
	
	
//	@Query("select u from Propriedade u where u.firstname = ?")
//	List<Propriedade> findByFirstname(String firstname);
	
//	@Query("select u from Propriedade u where u.firstname = :name or u.lastname = :name")
//	List<Propriedade> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
