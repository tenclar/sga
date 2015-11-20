package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.CadeiaProdutiva;
import br.gov.ac.seap.pga.repository.CadeiaProdutivaRepository;

@Service
@Transactional
public class CadeiaProdutivaService {

	@Autowired
	private CadeiaProdutivaRepository cadeiaprodutivaRepository;

	
	public CadeiaProdutiva findById(Long id){
		return this.cadeiaprodutivaRepository.findById(id);
	}
	
	
	public List<CadeiaProdutiva> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.cadeiaprodutivaRepository.findByNameLike(arg0);
	}
	
	public List<CadeiaProdutiva> findListByDescricaoLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.cadeiaprodutivaRepository.findByNameLike(arg0);
	}
	
	

	public List<CadeiaProdutiva> findAll() {
		return this.cadeiaprodutivaRepository.findAll(this.sortByIdDesc());
	}
	
	public List<CadeiaProdutiva> findAllOrderBySiglaDesc(){
		return this.cadeiaprodutivaRepository.findAll(this.sortBySiglaDesc()); 
	}
	public List<CadeiaProdutiva> findAllOrderBySigla(){
		return this.cadeiaprodutivaRepository.findAll(new Sort(Sort.Direction.ASC, "Sigla")); 
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	private Sort sortBySiglaDesc() {
		return new Sort(Sort.Direction.DESC, "Sigla");
	}

	public void save(CadeiaProdutiva cadeiaprodutiva) {
		this.cadeiaprodutivaRepository.save(cadeiaprodutiva);
	}
}
