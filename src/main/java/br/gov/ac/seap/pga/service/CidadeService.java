package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Cidade;
import br.gov.ac.seap.pga.model.Estado;
import br.gov.ac.seap.pga.repository.CidadeRepository;

@Service
@Transactional
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	
	public Cidade findById(Long id){
		return this.cidadeRepository.findById(id);
	}
	
	
	public List<Cidade> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.cidadeRepository.findByNomeLike(arg0);
	}
	public List<Cidade> findListByEstado(Estado arg0){
		
		return this.cidadeRepository.findByEstadoOrderByIdDesc(arg0);
	}

	public List<Cidade> findListByEstadoId(Long arg0){
		
		return this.cidadeRepository.findByEstadoIdOrderByIdDesc(arg0);
	}
	
	

	public List<Cidade> findAll() {
		return this.cidadeRepository.findAll(this.sortByIdDesc());
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}

	public void save(Cidade arg0) {
		this.cidadeRepository.save(arg0);
	}
}
