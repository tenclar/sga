package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Ocupacao;
import br.gov.ac.seap.pga.repository.OcupacaoRepository;

@Service
@Transactional
public class OcupacaoService {

	@Autowired
	private OcupacaoRepository ocupacaoRepository;

	
	public Ocupacao findById(Long id){
		return this.ocupacaoRepository.findById(id);
	}
	
	
	public List<Ocupacao> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.ocupacaoRepository.findByNameLike(arg0);
	}
	

	public List<Ocupacao> findAll() {
		return this.ocupacaoRepository.findAll(this.sortByIdDesc());
	}
		
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Ocupacao ocupacao) {
		this.ocupacaoRepository.save(ocupacao);
	}
}
