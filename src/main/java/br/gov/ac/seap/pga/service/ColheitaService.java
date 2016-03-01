package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Colheita;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.ColheitaRepository;

@Service
@Transactional
public class ColheitaService {

	@Autowired
	private ColheitaRepository colheitaRepository;

	
	public Colheita findById(Long id){
		return this.colheitaRepository.findById(id);
	}
	
//	
//	public List<Colheita> findListByNomeLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.colheitaRepository.findByNameLike(arg0);
//	}
//	
//	public List<Colheita> findListByDescriptionLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.colheitaRepository.findByDescricaoLike(arg0);
//	}
//	
//	
	

	public List<Colheita> findAll() {
		return this.colheitaRepository.findAll(this.sortByIdDesc());
	}
	
	public List<Colheita> findByProducao(Producao p) {
		return this.colheitaRepository.findByProducaoOrderByIdDesc(p);
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Colheita colheita) {
		this.colheitaRepository.save(colheita);
	}
}
