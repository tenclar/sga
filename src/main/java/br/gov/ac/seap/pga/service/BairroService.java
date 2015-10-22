package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Bairro;
import br.gov.ac.seap.pga.model.Cidade;
import br.gov.ac.seap.pga.repository.BairroRepository;

@Service
@Transactional
public class BairroService {

	@Autowired
	private BairroRepository cidadeRepository;

	
	public Bairro findById(Long id){
		return this.cidadeRepository.findById(id);
	}
	
	
	public List<Bairro> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.cidadeRepository.findByNomeLike(arg0);
	}
	public List<Bairro> findListByCidade(Cidade arg0){
		
		return this.cidadeRepository.findByCidadeOrderByIdDesc(arg0);
	}
	
	
	

	public List<Bairro> findAll() {
		return this.cidadeRepository.findAll(this.sortByIdDesc());
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}

	public void save(Bairro arg0) {
		this.cidadeRepository.save(arg0);
	}
}
