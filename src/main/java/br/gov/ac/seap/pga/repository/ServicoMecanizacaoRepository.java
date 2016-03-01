package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.ServicoMecanizacao;

@Repository
public interface ServicoMecanizacaoRepository extends CrudRepository<ServicoMecanizacao, Integer> {


	ServicoMecanizacao findById(Long arg0);	
		
	List<ServicoMecanizacao> findByNomeLike(String arg0);
	List<ServicoMecanizacao> findByDescricaoLike(String arg0);
	List<ServicoMecanizacao> findAll(Sort sort);
	
	
	
//	@Query("select u from ServicoMecanizacao u where u.firstname = ?")
//	List<ServicoMecanizacao> findByFirstname(String firstname);
	
//	@Query("select u from ServicoMecanizacao u where u.firstname = :name or u.lastname = :name")
//	List<ServicoMecanizacao> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
