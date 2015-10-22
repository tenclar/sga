package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.model.Authority;
import br.gov.ac.seap.pga.service.AuthorityService;



@Controller
@ManagedBean
public class PermissionController   {
	


	@Autowired
	private AuthorityService authorityService;
	
	private Authority authority;
	
	
	
			
	public Authority getAuthority() {
		return authority;
	}


	public void setAuthority(Authority authority) {
		this.authority = authority;
	}


	public List<Authority> getList(){
		return this.authorityService.findAll();
		
	}
	
			
	public void salvar(){
	 this.authorityService.save(authority); 
	}
	
	public void novo(){
		this.authority = new Authority();
	}

	public List<SelectItem> getSelects() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

			for (Authority a : this.authorityService.findAll()) {
				retorno.add(new SelectItem(a, a.getDescription()));
			}
			
		return retorno;
	}
	
	
	
	

}
