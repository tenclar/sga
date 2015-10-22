package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Estado;
import br.gov.ac.seap.pga.repository.EstadoRepository;

@Service
@Transactional
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	
	public Estado findById(Long id){
		return this.estadoRepository.findById(id);
	}
	
	
	public List<Estado> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.estadoRepository.findByNomeLike(arg0);
	}
	public List<Estado> findListBySigla(String arg0){
		
		return this.estadoRepository.findBySiglaOrderByIdDesc(arg0);
	}
	
	public Estado findBySigla(String arg0){
		return this.estadoRepository.findBySigla(arg0);
	}
	

	public List<Estado> findAll() {
		return this.estadoRepository.findAll(this.sortByIdDesc());
	}
	
	public List<Estado> findAllOrderBySiglaDesc(){
		return this.estadoRepository.findAll(this.sortBySiglaDesc()); 
	}
	public List<Estado> findAllOrderBySigla(){
		return this.estadoRepository.findAll(new Sort(Sort.Direction.ASC, "Sigla")); 
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	private Sort sortBySiglaDesc() {
		return new Sort(Sort.Direction.DESC, "Sigla");
	}

	public void save(Estado estado) {
		this.estadoRepository.save(estado);
	}
}
