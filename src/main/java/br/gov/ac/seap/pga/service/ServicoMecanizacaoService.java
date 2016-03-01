package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.ServicoMecanizacao;
import br.gov.ac.seap.pga.repository.ServicoMecanizacaoRepository;

@Service
@Transactional
public class ServicoMecanizacaoService {

	@Autowired
	private ServicoMecanizacaoRepository servicoMecanizacaoRepository;

	
	public ServicoMecanizacao findById(Long id){
		return this.servicoMecanizacaoRepository.findById(id);
	}
	
	
	public List<ServicoMecanizacao> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.servicoMecanizacaoRepository.findByNomeLike(arg0);
	}
	
	public List<ServicoMecanizacao> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.servicoMecanizacaoRepository.findByDescricaoLike(arg0);
	}
	
	
	

	public List<ServicoMecanizacao> findAll() {
		return this.servicoMecanizacaoRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(ServicoMecanizacao servicoMecanizacao) {
		this.servicoMecanizacaoRepository.save(servicoMecanizacao);
	}
}
