package br.gov.ac.seap.pga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.ac.seap.pga.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	//@Query("SELECT u from User u inner join Authority  where u.email = ?")
	User findByEmail(String email);
	User findByCpf(String cpf);
	
	User findById(Long id);
	
	
	User findByUsername(String username);
	
	List<User> findByCpfOrderByIdDesc(String cpf);
	List<User> findByFullName(String fullname);
	List<User> findByFullNameLike(String fullname);	
	List<User> findAll(Sort sort);
	
//	@Query("select u from User u where u.firstname = ?")
//	List<User> findByFirstname(String firstname);
	
//	@Query("select u from User u where u.firstname = :name or u.lastname = :name")
//	List<User> findByFirstnameOrLastname(@Param("namse") String name);
	
	
}
