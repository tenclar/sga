package br.gov.ac.seap.pga.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.gov.ac.seap.pga.model.User;
import br.gov.ac.seap.pga.service.UserService;


public class BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User userLogin;

	@Autowired
	private UserService userService;
	private String username;

	protected void login() {

		SecurityContext scontext = SecurityContextHolder.getContext();
		if (scontext instanceof SecurityContext) {
			Authentication authentication = scontext.getAuthentication();
			if (authentication instanceof Authentication) {
				// usersec.setLogin(((User)authentication.getPrincipal()).getUsername());

				username = (((User) authentication.getPrincipal()).getUsername());
				this.userLogin = this.userService.findByUsername(username);
				System.out.println("Autenticação login: " + ((User) authentication.getPrincipal()).getUsername());

			}
		}

	}

	protected void login4() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

		this.userLogin = (User) session.getAttribute("user");
		if (userLogin == null) {
			username = request.getRemoteUser();
			this.userLogin = this.userService.findByUsername(username);
			session.setAttribute("user", userLogin);

		}

	//	System.out.println("[BaseBeans ] login4 User " + this.userLogin.getUsername());

	}

	public User getUserLogin() {

		this.login4();
		return userLogin;
	}
	
	public User getUser(){
		
		return userLogin;
	}

	
}
