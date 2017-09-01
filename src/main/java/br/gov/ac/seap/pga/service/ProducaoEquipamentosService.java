package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.ProducaoEquipamentos;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.repository.ProducaoEquipamentosRepository;

@Service
@Transactional
public class ProducaoEquipamentosService {

	@Autowired
	private ProducaoEquipamentosRepository producaoEquipamentosRepository;

	
	public ProducaoEquipamentos findById(Long id){
		return this.producaoEquipamentosRepository.findById(id);
	}
	

	public ProducaoEquipamentos findOne(Long id){
		return this.producaoEquipamentosRepository.findOne(id);
	}
	
//	
//	public List<ProducaoEquipamentos> findListByNomeLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.producaoEquipamentosRepository.findByNameLike(arg0);
//	}
//	
//	public List<ProducaoEquipamentos> findListByDescriptionLike(String arg0){
//		arg0 = "%"+arg0+"%";
//		return this.producaoEquipamentosRepository.findByDescricaoLike(arg0);
//	}
//	
//	
	

	public List<ProducaoEquipamentos> findAll() {
		return this.producaoEquipamentosRepository.findAll(this.sortByIdDesc());
	}
	
	public List<ProducaoEquipamentos> findByProducao(Long p) {
		return this.producaoEquipamentosRepository.findByProducaoIdOrderByIdDesc(p);
	}
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(ProducaoEquipamentos producaoEquipamentos) {
		this.producaoEquipamentosRepository.save(producaoEquipamentos);
	}
}
