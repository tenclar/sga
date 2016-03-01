package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Plantio;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.PlantioRepository;

@Service
@Transactional
public class PlantioService {

	@Autowired
	private PlantioRepository plantioRepository;

	
	public Plantio findById(Long id){
		return this.plantioRepository.findById(id);
	}
	
//	
//	public List<Plantio> findListByNomeLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.plantioRepository.findByNameLike(arg0);
//	}
//	
//	public List<Plantio> findListByDescriptionLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.plantioRepository.findByDescricaoLike(arg0);
//	}
//	
//	
	

	public List<Plantio> findAll() {
		return this.plantioRepository.findAll(this.sortByIdDesc());
	}
	
	public List<Plantio> findByProducao(Producao p) {
		return this.plantioRepository.findByProducaoOrderByIdDesc(p);
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Plantio plantio) {
		this.plantioRepository.save(plantio);
	}
}
