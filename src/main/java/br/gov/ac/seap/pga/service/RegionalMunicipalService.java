package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.RegionalMunicipal;
import br.gov.ac.seap.pga.repository.RegionalMunicipalRepository;

@Service
@Transactional
public class RegionalMunicipalService {

	@Autowired
	private RegionalMunicipalRepository regionalMunicipalRepository;

	
	public RegionalMunicipal findById(Long id){
		return this.regionalMunicipalRepository.findById(id);
	}
	
	
	public List<RegionalMunicipal> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.regionalMunicipalRepository.findByNomeLike(arg0);
	}

	public List<RegionalMunicipal> findListByDescricaoLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.regionalMunicipalRepository.findByDescricaoLike(arg0);
	}
	
	
	public List<RegionalMunicipal> findAll() {
		return this.regionalMunicipalRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}


	public void save(RegionalMunicipal regionalMunicipal) {
		this.regionalMunicipalRepository.save(regionalMunicipal);
	}
}
