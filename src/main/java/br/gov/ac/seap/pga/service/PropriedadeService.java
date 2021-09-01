package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Propriedade;
import br.gov.ac.seap.pga.repository.PropriedadeRepository;

@Service
@Transactional
public class PropriedadeService {

	@Autowired
	private PropriedadeRepository propriedadeRepository;
	
	
	public Propriedade findOneById(Long id){
		return this.propriedadeRepository.findOne(id);
	}
	
	public Propriedade findById(Long id){
		return this.propriedadeRepository.findById(id);
	}
	
	public  List<Propriedade> findByProdutorId(Long id){
		return this.propriedadeRepository.findByProdutorId(id);
	}
	
	public List<Propriedade> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.propriedadeRepository.findByNomeLike(arg0);
	}
	
	public List<Propriedade> findListByProdutorNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.propriedadeRepository.findByProdutorNameLike(arg0);
	}
	

	public List<Propriedade> findAll() {
		return this.propriedadeRepository.findAll(this.sortByIdDesc());
	}
	public List<Propriedade> findAllPropriedade() {
		return this.propriedadeRepository.findAllPropriedade();
	}
	
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Propriedade propriedade) {
		this.propriedadeRepository.save(propriedade);
	}
}
