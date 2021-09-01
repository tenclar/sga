package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.Acao;
import br.gov.ac.seap.pga.model.Producao;

@Repository
public interface AcaoRepository extends CrudRepository<Acao, Integer> {


	Acao findById(Long arg0);	
	List<Acao> findByProducao(Producao arg0);	
	List<Acao> findByDescriptionLike(String arg0);	
	
	List<Acao> findAll(Sort sort);
	
	@Query("select a from Acao a fetch all properties")
	List<Acao> findAcoes();
	
	
	
//	@Query("select u from Acao u where u.firstname = ?")
//	List<Acao> findByFirstname(String firstname);
	
//	@Query("select u from Acao u where u.firstname = :name or u.lastname = :name")
//	List<Acao> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
