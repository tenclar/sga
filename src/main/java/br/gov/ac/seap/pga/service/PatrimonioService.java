package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Patrimonio;
import br.gov.ac.seap.pga.repository.PatrimonioRepository;

@Service
@Transactional
public class PatrimonioService {

	@Autowired
	private PatrimonioRepository patrimonioRepository;

	
	public Patrimonio findById(Long id){
		return this.patrimonioRepository.findById(id);
	}
	
	
	public List<Patrimonio> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.patrimonioRepository.findByNameLike(arg0);
	}
	
	
	public List<Patrimonio> findListByDescription(String arg0){
		arg0 = "%"+arg0+"%";
		return this.patrimonioRepository.findByDescriptionLike(arg0);
	}
	
	

	public List<Patrimonio> findAll() {
		return this.patrimonioRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Patrimonio patrimonio) {
		this.patrimonioRepository.save(patrimonio);
	}
}
