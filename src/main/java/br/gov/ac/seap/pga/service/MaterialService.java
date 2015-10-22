package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Material;
import br.gov.ac.seap.pga.repository.MaterialRepository;

@Service
@Transactional
public class MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	
	public Material findById(Long id){
		return this.materialRepository.findById(id);
	}
	
	
	public List<Material> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.materialRepository.findByNameLike(arg0);
	}
	
	public List<Material> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.materialRepository.findByDescricaoLike(arg0);
	}
	
	
	

	public List<Material> findAll() {
		return this.materialRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Material material) {
		this.materialRepository.save(material);
	}
}
