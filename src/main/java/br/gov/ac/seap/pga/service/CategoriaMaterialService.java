package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.CategoriaMaterial;
import br.gov.ac.seap.pga.repository.CategoriaMaterialRepository;

@Service
@Transactional
public class CategoriaMaterialService {

	@Autowired
	private CategoriaMaterialRepository categoriaMaterialRepository;

	
	public CategoriaMaterial findById(Long id){
		return this.categoriaMaterialRepository.findById(id);
	}
	
	
	public List<CategoriaMaterial> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.categoriaMaterialRepository.findByNomeLike(arg0);
	}
	
	public List<CategoriaMaterial> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.categoriaMaterialRepository.findByDescriptionLike(arg0);
	}
	
	
	

	public List<CategoriaMaterial> findAll() {
		return this.categoriaMaterialRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(CategoriaMaterial categoriaMaterial) {
		this.categoriaMaterialRepository.save(categoriaMaterial);
	}
}
