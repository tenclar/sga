package br.gov.ac.seap.pga.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
@SessionScoped
public class LoginController extends BaseController {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	
//	protected void login2() {
//		//user = new User();
//		username = "";
//		FacesContext context = FacesContext.getCurrentInstance();
//		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//		User user = (User) session.getAttribute("user");
//		if (user == null) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			username = auth.getName();
//			user = this.userService.findByUsername(username);
//			session.setAttribute("user", user);
//			System.out.println("login2 username " + username);
//			System.out.println("login2 User " + user.getUsername());
//		}
//	}

	// protected void login3() {
	//
	// username = "";
	// FacesContext context = FacesContext.getCurrentInstance();
	// HttpServletRequest request = (HttpServletRequest)
	// context.getExternalContext().getRequest();
	//
	// username = request.getRemoteUser();
	// this.userService.findByUsername(username);
	// System.out.println("login3 username " + username);
	// System.out.println("login3 User " + user.getUsername());
	//
	// }

}
