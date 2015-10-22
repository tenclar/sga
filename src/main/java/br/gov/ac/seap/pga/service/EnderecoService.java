package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Bairro;
import br.gov.ac.seap.pga.model.Endereco;
import br.gov.ac.seap.pga.repository.EnderecoRepository;

@Service
@Transactional
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	
	public Endereco findById(Long id){
		return this.enderecoRepository.findById(id);
	}
	
	
	public List<Endereco> findListByLogradouroLike(String arg0){
		arg0 = "%"+arg0+"%";
		return this.enderecoRepository.findByLogradouroLike(arg0);
	}
	public List<Endereco> findListByBairroAndLogradouroLike(Bairro arg0, String arg1){
		arg1 = "%"+arg1+"%";
		return this.enderecoRepository.findByBairroAndLogradouroLike(arg0, arg1, this.pageable());
	}
	
	public List<Endereco> findListByCidadeAndLogradouroLike(Long arg0, String arg1){
		arg1 = "%"+arg1+"%";
		return this.enderecoRepository.findByCidadeIdAndLogradouroLike(arg0, arg1, this.pageable());
	}
	
	

	public List<Endereco> findAll() {
		return this.enderecoRepository.findAll(this.sortByIdDesc());
	}

	private Pageable pageable(){
		return new PageRequest(0, 20);
	}
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}

	public void save(Endereco arg0) {
		this.enderecoRepository.save(arg0);
	}
}
