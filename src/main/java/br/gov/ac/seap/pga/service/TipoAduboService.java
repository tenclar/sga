package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.TipoAdubo;
import br.gov.ac.seap.pga.repository.TipoAduboRepository;

@Service
@Transactional
public class TipoAduboService {

	@Autowired
	private TipoAduboRepository tipoAduboRepository;

	
	public TipoAdubo findById(Long id){
		return this.tipoAduboRepository.findById(id);
	}
	
	
	public List<TipoAdubo> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.tipoAduboRepository.findByNameLike(arg0);
	}
	
	public List<TipoAdubo> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.tipoAduboRepository.findByDescricaoLike(arg0);
	}
	
	
	

	public List<TipoAdubo> findAll() {
		return this.tipoAduboRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(TipoAdubo tipoAdubo) {
		this.tipoAduboRepository.save(tipoAdubo);
	}
}
