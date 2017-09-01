package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.model.Setor;
import br.gov.ac.seap.pga.service.SetorService;
@Controller
@Scope("view")
public class SetorController extends BaseController  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Setor setor;
	private FacesContext context;
	
	
	@Autowired
	private SetorService setorService;

	

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Setor> getList() {
		return this.setorService.findAll();
		 
	}

	


	public List<SelectItem> getSelectItens() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (Setor p : this.setorService.findAll()) {
			retorno.add(new SelectItem(p, p.getDescription()));

		}
		return retorno;
	}
	
	public void salvar() {
		context = FacesContext.getCurrentInstance();
		try {

			this.setorService.save(setor);

			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Sucesso",
					"Cadastro Efetuado! "));

		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Erro",
					"Cadastro Não Efetuado! " + e.getMessage()));
		}

	}

	
	public void novo() {
		this.setor = new Setor();
		this.setor.setUser(super.getUserLogin());
	}
	
   

}
