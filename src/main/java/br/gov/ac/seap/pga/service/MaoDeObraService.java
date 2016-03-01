package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.MaoDeObra;
import br.gov.ac.seap.pga.repository.MaoDeObraRepository;

@Service
@Transactional
public class MaoDeObraService {

	@Autowired
	private MaoDeObraRepository maoDeObraRepository;

	
	public MaoDeObra findById(Long id){
		return this.maoDeObraRepository.findById(id);
	}
	
	
	public List<MaoDeObra> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.maoDeObraRepository.findByNameLike(arg0);
	}
	
	public List<MaoDeObra> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.maoDeObraRepository.findByDescricaoLike(arg0);
	}
	
	
	

	public List<MaoDeObra> findAll() {
		return this.maoDeObraRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(MaoDeObra maoDeObra) {
		this.maoDeObraRepository.save(maoDeObra);
	}
}
