package br.gov.ac.seap.pga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ac.seap.pga.model.User;
import br.gov.ac.seap.pga.repository.UserRepository;




@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		User user = null;
		try {
			return this.userRepository.findByUsername(arg0);

		} catch (UsernameNotFoundException e) {
			user = null;
		} catch (DataAccessException e) {
			user = null;

			return user;
		}
		return user;
	}
}
