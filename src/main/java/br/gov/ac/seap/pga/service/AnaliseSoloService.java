package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.AnaliseSolo;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.AnaliseSoloRepository;

@Service
@Transactional
public class AnaliseSoloService {

	@Autowired
	private AnaliseSoloRepository analiseSoloRepository;

	
	public AnaliseSolo findById(Long id){
		return this.analiseSoloRepository.findById(id);
	}
	
//	
//	public List<AnaliseSolo> findListByNomeLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.analiseSoloRepository.findByNameLike(arg0);
//	}
//	
//	public List<AnaliseSolo> findListByDescriptionLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.analiseSoloRepository.findByDescricaoLike(arg0);
//	}
//	
//	
	

	public List<AnaliseSolo> findAll() {
		return this.analiseSoloRepository.findAll(this.sortByIdDesc());
	}
	
	public List<AnaliseSolo> findByProducao(Producao p) {
		return this.analiseSoloRepository.findByProducaoOrderByIdDesc(p);
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(AnaliseSolo analiseSolo) {
		this.analiseSoloRepository.save(analiseSolo);
	}
}
