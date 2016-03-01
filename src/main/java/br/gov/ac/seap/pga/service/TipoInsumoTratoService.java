package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.TipoInsumoTrato;
import br.gov.ac.seap.pga.repository.TipoInsumoTratoRepository;

@Service
@Transactional
public class TipoInsumoTratoService {

	@Autowired
	private TipoInsumoTratoRepository tipoInsumoTratoRepository;

	
	public TipoInsumoTrato findById(Long id){
		return this.tipoInsumoTratoRepository.findById(id);
	}
	
	
	public List<TipoInsumoTrato> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.tipoInsumoTratoRepository.findByNameLike(arg0);
	}
	
	public List<TipoInsumoTrato> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.tipoInsumoTratoRepository.findByDescricaoLike(arg0);
	}
	
	
	

	public List<TipoInsumoTrato> findAll() {
		return this.tipoInsumoTratoRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(TipoInsumoTrato tipoInsumoTrato) {
		this.tipoInsumoTratoRepository.save(tipoInsumoTrato);
	}
}
