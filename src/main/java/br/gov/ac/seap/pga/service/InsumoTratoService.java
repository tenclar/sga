package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.InsumoTrato;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.InsumoTratoRepository;

@Service
@Transactional
public class InsumoTratoService {

	@Autowired
	private InsumoTratoRepository insumoTratoRepository;

	public InsumoTrato findById(Long id){
		return this.insumoTratoRepository.findById(id);
	}
	public InsumoTrato findOne(Long id){
		return this.insumoTratoRepository.findOne(id);
	}
	
	
//	public List<InsumoTrato> findListByNomeLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.insumoTratoRepository.findByNameLike(arg0);
//	}
//	
//	public List<InsumoTrato> findListByDescriptionLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.insumoTratoRepository.findByDescricaoLike(arg0);
//	}
//	
//	
	public List<InsumoTrato> findByProducao(Long p) {
		return this.insumoTratoRepository.findByProducaoIdOrderByIdDesc(p);
	}

	public List<InsumoTrato> findAll() {
		return this.insumoTratoRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(InsumoTrato insumoTrato) {
		this.insumoTratoRepository.save(insumoTrato);
	}
}
