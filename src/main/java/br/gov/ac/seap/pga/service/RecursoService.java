package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Recurso;
import br.gov.ac.seap.pga.repository.RecursoRepository;

@Service
@Transactional
public class RecursoService {

	@Autowired
	private RecursoRepository recursoRepository;

	
	public Recurso findById(Long id){
		return this.recursoRepository.findById(id);
	}
	
	
	public List<Recurso> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.recursoRepository.findByNomeLike(arg0);
	}
	
	public List<Recurso> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.recursoRepository.findByDescriptionLike(arg0);
	}
	
	
	
	

	public List<Recurso> findAll() {
		return this.recursoRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Recurso recurso) {
		this.recursoRepository.save(recurso);
	}
}
