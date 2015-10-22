package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.OrganizacaoSocial;
import br.gov.ac.seap.pga.repository.OrganizacaoSocialRepository;

@Service
@Transactional
public class OrganizacaoSocialService {

	@Autowired
	private OrganizacaoSocialRepository organizacaosocialRepository;

	
	public OrganizacaoSocial findById(Long id){
		return this.organizacaosocialRepository.findById(id);
	}
	
	
	public List<OrganizacaoSocial> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.organizacaosocialRepository.findByNomeLike(arg0);
	}
	
	
	
	

	public List<OrganizacaoSocial> findAll() {
		return this.organizacaosocialRepository.findAll(this.sortByIdDesc());
	}
	
	public List<OrganizacaoSocial> findAllOrderBySiglaDesc(){
		return this.organizacaosocialRepository.findAll(this.sortBySiglaDesc()); 
	}
	public List<OrganizacaoSocial> findAllOrderBySigla(){
		return this.organizacaosocialRepository.findAll(new Sort(Sort.Direction.ASC, "Sigla")); 
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	private Sort sortBySiglaDesc() {
		return new Sort(Sort.Direction.DESC, "Sigla");
	}

	public void save(OrganizacaoSocial organizacaosocial) {
		this.organizacaosocialRepository.save(organizacaosocial);
	}
}
