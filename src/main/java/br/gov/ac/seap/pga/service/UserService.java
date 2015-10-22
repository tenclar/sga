package br.gov.ac.seap.pga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.User;
import br.gov.ac.seap.pga.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	public User findById(Long id){
		return this.userRepository.findById(id);
	}
	
	public User findByUsername(String arg0){
		return this.userRepository.findByUsername(arg0);
	}
	
	public List<User> findListByFullName(String fullname){
		return this.userRepository.findByFullName(fullname);
	}
	public List<User> findListByCpf(String cpf){
		return this.userRepository.findByCpfOrderByIdDesc(cpf);
	}
	public List<User> findListByFullNameLike(String fullname){
		fullname = "%"+fullname+"%";
		return this.userRepository.findByFullNameLike(fullname);
	}
	
	

	public List<User> findAll() {
		return this.userRepository.findAll(this.sortByIdDesc());
	}

	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "Id");
	}

	public void save(User user) {
		this.userRepository.save(user);
	}
}
