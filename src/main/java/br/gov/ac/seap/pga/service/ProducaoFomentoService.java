package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.ProducaoFomento;
import br.gov.ac.seap.pga.repository.ProducaoFomentoRepository;

@Service
@Transactional
public class ProducaoFomentoService {

	@Autowired
	private ProducaoFomentoRepository producaoFomentoRepository;

	
	public ProducaoFomento findById(Long id){
		return this.producaoFomentoRepository.findById(id);
	}
	

	public ProducaoFomento findOne(Long id){
		return this.producaoFomentoRepository.findOne(id);
	}
	
//	
//	public List<ProducaoFomento> findListByNomeLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.producaoFomentoRepository.findByNameLike(arg0);
//	}
//	
//	public List<ProducaoFomento> findListByDescriptionLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.producaoFomentoRepository.findByDescricaoLike(arg0);
//	}
//	
//	
	

	public List<ProducaoFomento> findAll() {
		return this.producaoFomentoRepository.findAll(this.sortByIdDesc());
	}
	
	public List<ProducaoFomento> findByProducao(Long p) {
		return this.producaoFomentoRepository.findByProducaoIdOrderByIdDesc(p);
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(ProducaoFomento producaoFomento) {
		this.producaoFomentoRepository.save(producaoFomento);
	}
}
