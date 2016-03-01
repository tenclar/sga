package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.TipoPreparoArea;
import br.gov.ac.seap.pga.repository.TipoPreparoAreaRepository;

@Service
@Transactional
public class TipoPreparoAreaService {

	@Autowired
	private TipoPreparoAreaRepository tipoPreparoAreaRepository;

	
	public TipoPreparoArea findById(Long id){
		return this.tipoPreparoAreaRepository.findById(id);
	}
	
	
	public List<TipoPreparoArea> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.tipoPreparoAreaRepository.findByNameLike(arg0);
	}
	
	public List<TipoPreparoArea> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.tipoPreparoAreaRepository.findByDescricaoLike(arg0);
	}
	
	
	

	public List<TipoPreparoArea> findAll() {
		return this.tipoPreparoAreaRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(TipoPreparoArea tipoPreparoArea) {
		this.tipoPreparoAreaRepository.save(tipoPreparoArea);
	}
}
