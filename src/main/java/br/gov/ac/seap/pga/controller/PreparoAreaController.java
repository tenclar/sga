package br.gov.ac.seap.pga.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIForm;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.PreparoArea;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.service.PreparoAreaService;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class PreparoAreaController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PreparoAreaService preparoAreaService;

	@Autowired
	private ProducaoService producaoService;

	private PreparoArea preparoArea;

	private List<PreparoArea> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";

	public PreparoAreaController() {

	}
	
	
	
	
	

	public void actsalvar() {
		
		try {

			this.preparoAreaService.save(preparoArea);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void actsalvarnext() {
		this.preparoAreaService.save(preparoArea);
		FacesUtils.info(FacesUtils.mensages("message.save.success"));
	}

	public void actnovo() {
		this.preparoArea = new PreparoArea();
		this.preparoArea.setUser(super.getUserLogin());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actnovo(Producao p) {
		this.actnovo();
		this.preparoArea.setProducao(p);
	}

	public void acteditar() {
		preparoArea = this.preparoAreaService.findById(preparoArea.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar(PreparoArea a) {

	}

	public void actlista() {
		list = this.preparoAreaService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.preparoArea = new PreparoArea();
		// this.argumento = new String();
		this.list = null;

		// FacesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	public DataModel<PreparoArea> getLista(Producao p) {
		DataModel<PreparoArea> dmLista = null;
		if (p != null) {
			if (p.getId() != null) {
				//System.out.println("id producao: " + p.getId());
				//Producao prod = this.producaoService.findById(p.getId());
				List<PreparoArea> lista = this.preparoAreaService.findByProducao(p.getId());
				if (lista != null) {
					dmLista = new ListDataModel<PreparoArea>(lista);
				}
			}
		}
		return dmLista;
	}

	// public void actpesquisa() {
	// 
	// actlimpa();
	// try {
	//
	// if ("nome".equals(this.tipopesquisa)) {
	// \ this.list = this.preparoAreaService.findListByNomeLike(argumento);
	// }
	// if ("description".equals(this.tipopesquisa)) {
	// this.list = this.preparoAreaService.findListByDescriptionLike(argumento);
	// }
	//
	// if (list.isEmpty()) {
	// throw new Exception();
	// }
	// } catch (Exception e) {
	// FacesUtils.aviso(FacesUtils.mensages("search.not.found") +
	// e.getMessage());
	// }finally{
	//
	// }
	//
	// }

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

	public PreparoArea getPreparoArea() {
		return preparoArea;
	}

	public void setPreparoArea(PreparoArea preparoArea) {
		this.preparoArea = preparoArea;
	}

	public List<PreparoArea> getList() {
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
