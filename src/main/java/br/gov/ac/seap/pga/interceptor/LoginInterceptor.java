package br.gov.ac.seap.pga.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.gov.ac.seap.pga.model.User;
import br.gov.ac.seap.pga.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	//@Autowired
	//private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		if (user == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//String username = auth.getName();
			//user = this.userService.findByUsername(username);
			user = ((User) auth.getPrincipal());
			session.setAttribute("user", user);
		}
        
		return super.preHandle(request, response, handler);
	}
}
