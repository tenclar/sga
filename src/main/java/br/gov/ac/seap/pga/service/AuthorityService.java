package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Authority;
import br.gov.ac.seap.pga.repository.AuthorityRepository;

@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityRepository autorityRepository;

	public Authority findByAuthority(String authority) {
		return this.autorityRepository.findByAuthority(authority);
	}
	
	public Authority findById(Long arg1) {
		return this.autorityRepository.findById(arg1);
	}
	

	public List<Authority> findAll() {
		return this.autorityRepository.findAll(this.sortByIdDesc());
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}

	public void save(Authority authority) {
		this.autorityRepository.save(authority);
	}
}
