package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.RegionalEstadual;
import br.gov.ac.seap.pga.repository.RegionalEstadualRepository;

@Service
@Transactional
public class RegionalEstadualService {

	@Autowired
	private RegionalEstadualRepository regionalEstadualRepository;

	
	public RegionalEstadual findById(Long id){
		return this.regionalEstadualRepository.findById(id);
	}
	
	
	public List<RegionalEstadual> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.regionalEstadualRepository.findByNomeLike(arg0);
	}

	public List<RegionalEstadual> findListByDescricaoLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.regionalEstadualRepository.findByDescricaoLike(arg0);
	}
	
	
	public List<RegionalEstadual> findAll() {
		return this.regionalEstadualRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(RegionalEstadual regionalEstadual) {
		this.regionalEstadualRepository.save(regionalEstadual);
	}
}
