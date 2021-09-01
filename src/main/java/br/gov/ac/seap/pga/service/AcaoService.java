package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Acao;
import br.gov.ac.seap.pga.repository.AcaoRepository;

@Service
@Transactional
public class AcaoService {

	@Autowired
	private AcaoRepository acaoRepository;

	
	public Acao findById(Long id){
		return this.acaoRepository.findById(id);
	}
	public List<Acao> findAcoes(){
		return this.acaoRepository.findAcoes();
	}
	
	
	public List<Acao> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.acaoRepository.findByDescriptionLike(arg0);
	}
	
	
	

	public List<Acao> findAll() {
		return this.acaoRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Acao acao) {
		this.acaoRepository.save(acao);
	}
}
