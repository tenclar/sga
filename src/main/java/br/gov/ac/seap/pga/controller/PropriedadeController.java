package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.Propriedade;
import br.gov.ac.seap.pga.service.PropriedadeService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class PropriedadeController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PropriedadeService propriedadeService;

	private Propriedade propriedade;

	private List<Propriedade> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;	

	private String argumento= "";

	private String tipopesquisa = "nome";
	
	
	
	

	
	
	

	public void actsalvar() {
		
		try {
			
			this.propriedadeService.save(propriedade);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (Propriedade p : this.propriedadeService.findAll()) {
			retorno.add(new SelectItem(p, p.getNome()));

		}
		return retorno;
	}
	
	public void actnovo() {
		this.propriedade = new Propriedade();
		
		this.propriedade.setUser(super.getUserLogin());

		
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		propriedade = this.propriedadeService.findById(propriedade.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.propriedadeService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.propriedade = new Propriedade();
		//this.argumento = new String();
		this.list = null;

		// FacesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	


	public void actpesquisa() {
		
		actlimpa();
		try {

			if ("propriedade".equals(this.tipopesquisa)) {
				this.list = this.propriedadeService.findListByNomeLike(argumento);
			}
			if ("produtor".equals(this.tipopesquisa)) {
				this.list = this.propriedadeService.findListByProdutorNomeLike(argumento);
			}

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			FacesUtils.aviso(FacesUtils.mensages("search.not.found") + e.getMessage());
		}finally{
			
		}

	}

	

	public String getTipopesquisa() {
		return tipopesquisa;
	}

	public void setTipopesquisa(String tipopesquisa) {
		this.tipopesquisa = tipopesquisa;
	}

	public boolean isPesquisando() {
		return EnumActionState.PESQUISA.equals(actionstate);
	}

	public boolean isAddEdit() {
		return EnumActionState.FORM.equals(actionstate);
	}

	public Propriedade getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(Propriedade propriedade) {
		this.propriedade = propriedade;
	}

	public List<Propriedade> getList() {
		return list;

	}

	public void setActionstate(EnumActionState actionstate) {
		this.actionstate = actionstate;
	}

	



	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

}
