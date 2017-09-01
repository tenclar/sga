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
import br.gov.ac.seap.pga.model.ServicoMecanizacao;
import br.gov.ac.seap.pga.model.TipoPreparoArea;
import br.gov.ac.seap.pga.service.TipoPreparoAreaService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@ViewScoped
public class TipoPreparoAreaController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoPreparoAreaService tipoPreparoAreaService;

	private TipoPreparoArea tipoPreparoArea;

	private List<TipoPreparoArea> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	

	private String argumento;

	private String tipopesquisa = "nome";

	

	public TipoPreparoAreaController() {

	}

	

	

	public void actsalvar() {
		
		try {
			
			this.tipoPreparoAreaService.save(tipoPreparoArea);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (TipoPreparoArea p : this.tipoPreparoAreaService.findAll()) {
			retorno.add(new SelectItem(p, p.getName()));

		}
		return retorno;
	}
	
	public void actnovo() {
		this.tipoPreparoArea = new TipoPreparoArea();
		
		this.tipoPreparoArea.setUser(super.getUserLogin());

		
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		tipoPreparoArea = this.tipoPreparoAreaService.findById(tipoPreparoArea.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.tipoPreparoAreaService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.tipoPreparoArea = new TipoPreparoArea();
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
				this.list = this.tipoPreparoAreaService.findListByNomeLike(argumento);
			}
			if ("description".equals(this.tipopesquisa)) {
				this.list = this.tipoPreparoAreaService.findListByDescriptionLike(argumento);
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

	public TipoPreparoArea getTipoPreparoArea() {
		return tipoPreparoArea;
	}

	public void setTipoPreparoArea(TipoPreparoArea tipoPreparoArea) {
		this.tipoPreparoArea = tipoPreparoArea;
	}

	public List<TipoPreparoArea> getList() {
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
