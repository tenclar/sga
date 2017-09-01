package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.RegionalMunicipal;
import br.gov.ac.seap.pga.service.RegionalMunicipalService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class RegionalMunicipalController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegionalMunicipalService regionalMunicipalService;

	private RegionalMunicipal regionalMunicipal;

	private List<RegionalMunicipal> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	

	private String argumento;

	private String tipopesquisa = "nome";

	

	public RegionalMunicipalController() {

	}

	

	

	public void actsalvar() {
		
		try {
			regionalMunicipal.setUser(getUser());
			this.regionalMunicipalService.save(regionalMunicipal);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlista();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (RegionalMunicipal p : this.regionalMunicipalService.findAll()) {
			retorno.add(new SelectItem(p, p.getNome()));

		}
		return retorno;
	}
	
	public void actnovo() {
		this.regionalMunicipal = new RegionalMunicipal();
		
		this.regionalMunicipal.setUser(super.getUserLogin());

		
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		regionalMunicipal = this.regionalMunicipalService.findById(regionalMunicipal.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.regionalMunicipalService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void  actlimpa() {
		
		this.regionalMunicipal = new RegionalMunicipal();
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

			if ("nome".equals(this.tipopesquisa)) {
				this.list = this.regionalMunicipalService.findListByNomeLike(argumento);
			}
			if ("descricao".equals(this.tipopesquisa)) {
				this.list = this.regionalMunicipalService.findListByDescricaoLike(argumento);
			}

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("search.not.found") + e.getMessage());
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

	public RegionalMunicipal getRegionalMunicipal() {
		return regionalMunicipal;
	}

	public void setRegionalMunicipal(RegionalMunicipal regionalMunicipal) {
		this.regionalMunicipal = regionalMunicipal;
	}

	public List<RegionalMunicipal> getList() {
		return list;

	}

	public void setActionstate(EnumActionState actionstate) {
		this.actionstate = actionstate;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

}
