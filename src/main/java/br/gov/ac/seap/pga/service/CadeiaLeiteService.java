package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.CadeiaLeite;
import br.gov.ac.seap.pga.model.InsumoTrato;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.CadeiaLeiteRepository;

@Service
@Transactional
public class CadeiaLeiteService {

	@Autowired
	private CadeiaLeiteRepository cadeiaLeiteRepository;

	
	public CadeiaLeite findById(Long id){
		return this.cadeiaLeiteRepository.findById(id);
	}
	

	public CadeiaLeite findOne(Long id){
		return this.cadeiaLeiteRepository.findOne(id);
	}
	
	public List<CadeiaLeite> findByProducao(Long id) {
		return this.cadeiaLeiteRepository.findByProducaoIdOrderByIdDesc(id);
	}

	public List<CadeiaLeite> findAllByQuery(){
		return this.cadeiaLeiteRepository.findAllByQuery();
	}
	

	public List<CadeiaLeite> findAll() {
		return this.cadeiaLeiteRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(CadeiaLeite cadeiaLeite) {
		this.cadeiaLeiteRepository.save(cadeiaLeite);
	}
}
