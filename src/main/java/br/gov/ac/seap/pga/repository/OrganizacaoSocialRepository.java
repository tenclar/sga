package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.OrganizacaoSocial;

@Repository
public interface OrganizacaoSocialRepository extends CrudRepository<OrganizacaoSocial, Integer> {


	OrganizacaoSocial findById(Long arg0);
	List<OrganizacaoSocial> findByNome(String arg0);	
	List<OrganizacaoSocial> findByNomeLike(String arg0);	
	List<OrganizacaoSocial> findAll(Sort sort);
	
	
	
//	@Query("select u from OrganizacaoSocial u where u.firstname = ?")
//	List<OrganizacaoSocial> findByFirstname(String firstname);
	
//	@Query("select u from OrganizacaoSocial u where u.firstname = :name or u.lastname = :name")
//	List<OrganizacaoSocial> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
