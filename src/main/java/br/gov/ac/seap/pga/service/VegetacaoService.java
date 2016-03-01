package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Vegetacao;
import br.gov.ac.seap.pga.repository.VegetacaoRepository;

@Service
@Transactional
public class VegetacaoService {

	@Autowired
	private VegetacaoRepository vegetacaoRepository;

	
	public Vegetacao findById(Long id){
		return this.vegetacaoRepository.findById(id);
	}
	
	
	public List<Vegetacao> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.vegetacaoRepository.findByNameLike(arg0);
	}
	
	public List<Vegetacao> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.vegetacaoRepository.findByDescricaoLike(arg0);
	}
	
	
	

	public List<Vegetacao> findAll() {
		return this.vegetacaoRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Vegetacao vegetacao) {
		this.vegetacaoRepository.save(vegetacao);
	}
}
