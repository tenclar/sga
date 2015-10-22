package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.Setor;
import br.gov.ac.seap.pga.repository.SetorRepository;

@Service
@Transactional
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;

	public Setor findBySetor(String description) {
		return this.setorRepository.findByDescription(description);
	}

	public List<Setor> findAll() {
		return this.setorRepository.findAll(this.sortByIdDesc());
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}

	public void save(Setor setor) {
		this.setorRepository.save(setor);
	}
}
