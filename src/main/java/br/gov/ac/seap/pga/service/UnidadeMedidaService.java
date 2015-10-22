package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.UnidadeMedida;
import br.gov.ac.seap.pga.repository.UnidadeMedidaRepository;

@Service
@Transactional
public class UnidadeMedidaService {

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	
	public UnidadeMedida findById(Long id){
		return this.unidadeMedidaRepository.findById(id);
	}
	
	
	public List<UnidadeMedida> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.unidadeMedidaRepository.findByNomeLike(arg0);
	}
	public List<UnidadeMedida> findListBySigla(String arg0){
		
		return this.unidadeMedidaRepository.findBySiglaOrderByIdDesc(arg0);
	}
	
	public UnidadeMedida findBySigla(String arg0){
		return this.unidadeMedidaRepository.findBySigla(arg0);
	}
	

	public List<UnidadeMedida> findAll() {
		return this.unidadeMedidaRepository.findAll(this.sortByIdDesc());
	}
	
	public List<UnidadeMedida> findAllOrderBySiglaDesc(){
		return this.unidadeMedidaRepository.findAll(this.sortBySiglaDesc()); 
	}
	public List<UnidadeMedida> findAllOrderBySigla(){
		return this.unidadeMedidaRepository.findAll(new Sort(Sort.Direction.ASC, "Sigla")); 
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	private Sort sortBySiglaDesc() {
		return new Sort(Sort.Direction.DESC, "Sigla");
	}

	public void save(UnidadeMedida unidadeMedida) {
		this.unidadeMedidaRepository.save(unidadeMedida);
	}
}
