package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.PlantioColheita;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.PlantioColheitaRepository;

@Service
@Transactional
public class PlantioColheitaService {

	@Autowired
	private PlantioColheitaRepository plantioColheitaRepository;

	
	public PlantioColheita findById(Long id){
		return this.plantioColheitaRepository.findById(id);
	}
	

	

	public List<PlantioColheita> findAll() {
		return this.plantioColheitaRepository.findAll(this.sortByIdDesc());
	}
	
	public List<PlantioColheita> findByProducao(Long p) {
		return this.plantioColheitaRepository.findByProducaoIdOrderByIdDesc(p);
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(PlantioColheita plantioColheita) {
		this.plantioColheitaRepository.save(plantioColheita);
	}
}
