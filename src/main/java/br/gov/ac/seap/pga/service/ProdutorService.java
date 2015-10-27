package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Produtor;
import br.gov.ac.seap.pga.repository.ProdutorRepository;

@Service
@Transactional
public class ProdutorService {

	@Autowired
	private ProdutorRepository produtorRepository;

	
	public Produtor findById(Long id){
		return this.produtorRepository.findById(id);
	}
	
	public Produtor findByName(String arg0){
		return this.produtorRepository.findByName(arg0);
	}
	public Produtor findByCpf(String arg0){
		return this.produtorRepository.findByCpf(arg0);
	}
	
	
	public List<Produtor> findListByCpf(String arg0){
		return this.produtorRepository.findByCpfOrderByIdDesc(arg0);
	}
	public List<Produtor> findListByNameLike(String fullname){
		fullname = "%"+fullname+"%";
		return this.produtorRepository.findByNameLike(fullname);
	}
	
	

	public List<Produtor> findAll() {
		return this.produtorRepository.findAll(this.sortByIdDesc());
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}

	public void save(Produtor produtor) {
		this.produtorRepository.save(produtor);
	}
}
