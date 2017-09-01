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
import br.gov.ac.seap.pga.model.PlantioColheita;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.service.PlantioColheitaService;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class PlantioColheitaController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PlantioColheitaService plantioColheitaService;

	@Autowired
	private ProducaoService producaoService;

	private PlantioColheita plantioColheita;

	private List<PlantioColheita> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";

	public PlantioColheitaController() {

	}

	public void actsalvar() {
		
		try {

			this.plantioColheitaService.save(plantioColheita);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void actsalvarnext() {
		this.plantioColheitaService.save(plantioColheita);
		FacesUtils.info(FacesUtils.mensages("message.save.success"));
	}

	public void actnovo() {
		this.plantioColheita = new PlantioColheita();
		this.plantioColheita.setUser(super.getUserLogin());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actnovo(Producao p) {
		this.actnovo();
		this.plantioColheita.setProducao(p);
	}

	public void acteditar() {
		plantioColheita = this.plantioColheitaService.findById(plantioColheita.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar(PlantioColheita a) {

	}

	public void actlista() {
		list = this.plantioColheitaService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {

		this.plantioColheita = new PlantioColheita();
		// this.argumento = new String();
		this.list = null;

		// FacesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	public DataModel<PlantioColheita> getLista(Producao p) {
		DataModel<PlantioColheita> dmLista = null;
		if (p != null) {
			if (p.getId() != null) {
				//System.out.println("id producao: " + p.getId());
				//Producao prod = this.producaoService.findById(p.getId());
				List<PlantioColheita> lista = this.plantioColheitaService.findByProducao(p.getId());
				if (lista != null) {
					dmLista = new ListDataModel<PlantioColheita>(lista);
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
	// \ this.list = this.plantioColheitaService.findListByNomeLike(argumento);
	// }
	// if ("description".equals(this.tipopesquisa)) {
	// this.list = this.plantioColheitaService.findListByDescriptionLike(argumento);
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

	public PlantioColheita getPlantioColheita() {
		return plantioColheita;
	}

	public void setPlantioColheita(PlantioColheita plantioColheita) {
		this.plantioColheita = plantioColheita;
	}

	public List<PlantioColheita> getList() {
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
