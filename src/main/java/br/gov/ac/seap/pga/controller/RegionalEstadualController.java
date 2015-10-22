package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.RegionalEstadual;
import br.gov.ac.seap.pga.service.RegionalEstadualService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@ViewScoped
public class RegionalEstadualController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegionalEstadualService regionalEstadualService;

	private RegionalEstadual regionalEstadual;

	private List<RegionalEstadual> list = null;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	

	private String argumento;

	private String tipopesquisa = "nome";

	

	public RegionalEstadualController() {

	}

	

	

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {
			regionalEstadual.setUser(getUser());
			this.regionalEstadualService.save(regionalEstadual);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlista();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (RegionalEstadual p : this.regionalEstadualService.findAll()) {
			retorno.add(new SelectItem(p, p.getNome()));

		}
		return retorno;
	}
	
	public void actnovo() {
		this.regionalEstadual = new RegionalEstadual();
		
		this.regionalEstadual.setUser(super.getUserLogin());

		
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		regionalEstadual = this.regionalEstadualService.findById(regionalEstadual.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.regionalEstadualService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void  actlimpa() {
		facesUtils = new FacesUtils();
		this.regionalEstadual = new RegionalEstadual();
		this.list = null;

		// facesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	


	public void actpesquisa() {
		facesUtils = new FacesUtils();
		actlimpa();
		try {

			if ("nome".equals(this.tipopesquisa)) {
				this.list = this.regionalEstadualService.findListByNomeLike(argumento);
			}
			if ("descricao".equals(this.tipopesquisa)) {
				this.list = this.regionalEstadualService.findListByDescricaoLike(argumento);
			}

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("search.not.found") + e.getMessage());
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

	public RegionalEstadual getRegionalEstadual() {
		return regionalEstadual;
	}

	public void setRegionalEstadual(RegionalEstadual regionalEstadual) {
		this.regionalEstadual = regionalEstadual;
	}

	public List<RegionalEstadual> getList() {
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
