package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.ProducaoRepository;

@Service
@Transactional
public class ProducaoService {

	@Autowired
	private ProducaoRepository producaoRepository;

	
	public Producao findById(Long id){
		return this.producaoRepository.findById(id);
	}

	
	public List<Producao> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.producaoRepository.findByDescricaoLike(arg0);
	}
	

	public List<Producao> findAll() {
		return this.producaoRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Producao producao) {
		this.producaoRepository.save(producao);
	}
}
