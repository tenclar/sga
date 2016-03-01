package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.PreparoArea;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.PreparoAreaRepository;

@Service
@Transactional
public class PreparoAreaService {

	@Autowired
	private PreparoAreaRepository preparoAreaRepository;

	
	public PreparoArea findById(Long id){
		return this.preparoAreaRepository.findById(id);
	}
	
//	
//	public List<PreparoArea> findListByNomeLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.preparoAreaRepository.findByNameLike(arg0);
//	}
//	
//	public List<PreparoArea> findListByDescriptionLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.preparoAreaRepository.findByDescricaoLike(arg0);
//	}
//	
//	
	

	public List<PreparoArea> findAll() {
		return this.preparoAreaRepository.findAll(this.sortByIdDesc());
	}
	
	public List<PreparoArea> findByProducao(Producao p) {
		return this.preparoAreaRepository.findByProducaoOrderByIdDesc(p);
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(PreparoArea preparoArea) {
		this.preparoAreaRepository.save(preparoArea);
	}
}
