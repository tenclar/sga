package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.CategoriaFormento;
import br.gov.ac.seap.pga.repository.CategoriaFormentoRepository;

@Service
@Transactional
public class CategoriaFormentoService {

	@Autowired
	private CategoriaFormentoRepository categoriaFormentoRepository;

	
	public CategoriaFormento findById(Long id){
		return this.categoriaFormentoRepository.findById(id);
	}
	
	
	public List<CategoriaFormento> findListByNomeLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.categoriaFormentoRepository.findByNomeLikeOrderByIdDesc(arg0);
	}
	public List<CategoriaFormento> findListByDescriptionLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.categoriaFormentoRepository.findByDescriptionLikeOrderByIdDesc(arg0);
	}
	

	

	public List<CategoriaFormento> findAll() {
		return this.categoriaFormentoRepository.findAll(this.sortByIdDesc());
	}
	
	
	
	
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}
	

	public void save(CategoriaFormento categoriaFormento) {
		this.categoriaFormentoRepository.save(categoriaFormento);
	}
}
