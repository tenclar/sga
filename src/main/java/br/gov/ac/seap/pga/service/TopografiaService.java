package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Topografia;
import br.gov.ac.seap.pga.repository.TopografiaRepository;

@Service
@Transactional
public class TopografiaService {

	@Autowired
	private TopografiaRepository topografiaRepository;

	
	public Topografia findById(Long id){
		return this.topografiaRepository.findById(id);
	}
	
	
	public List<Topografia> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.topografiaRepository.findByNameLike(arg0);
	}
	
	public List<Topografia> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.topografiaRepository.findByDescricaoLike(arg0);
	}
	
	
	

	public List<Topografia> findAll() {
		return this.topografiaRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(Topografia topografia) {
		this.topografiaRepository.save(topografia);
	}
}
