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
import br.gov.ac.seap.pga.model.ProducaoEquipamentos;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.service.ProducaoEquipamentosService;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class ProducaoEquipamentosController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProducaoEquipamentosService producaoEquipamentosService;

	@Autowired
	private ProducaoService producaoService;

	private ProducaoEquipamentos producaoEquipamentos;

	private List<ProducaoEquipamentos> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";

	public ProducaoEquipamentosController() {

	}

	public void actsalvar() {
		
		try {

			this.producaoEquipamentosService.save(producaoEquipamentos);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void actsalvarnext() {
		this.producaoEquipamentosService.save(producaoEquipamentos);
		FacesUtils.info(FacesUtils.mensages("message.save.success"));
	}

	public void actnovo() {
		this.producaoEquipamentos = new ProducaoEquipamentos();
		this.producaoEquipamentos.setUser(super.getUserLogin());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actnovo(Producao p) {
		this.actnovo();
		this.producaoEquipamentos.setProducao(p);
	}

	public void acteditar() {
		producaoEquipamentos = this.producaoEquipamentosService.findById(producaoEquipamentos.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar(ProducaoEquipamentos a) {

	}

	public void actlista() {
		list = this.producaoEquipamentosService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.producaoEquipamentos = new ProducaoEquipamentos();
		// this.argumento = new String();
		this.list = null;

		// FacesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	public DataModel<ProducaoEquipamentos> getLista(Producao p) {
		DataModel<ProducaoEquipamentos> dmLista = null;
		if (p != null) {
			if (p.getId() != null) {
				//System.out.println("id producao: " + p.getId());
				//Producao prod = this.producaoService.findById(p.getId());
				List<ProducaoEquipamentos> lista = this.producaoEquipamentosService.findByProducao(p.getId());
				if (lista != null) {
					dmLista = new ListDataModel<ProducaoEquipamentos>(lista);
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
	// \ this.list = this.producaoEquipamentosService.findListByNomeLike(argumento);
	// }
	// if ("description".equals(this.tipopesquisa)) {
	// this.list = this.producaoEquipamentosService.findListByDescriptionLike(argumento);
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

	public ProducaoEquipamentos getProducaoEquipamentos() {
		return producaoEquipamentos;
	}

	public void setProducaoEquipamentos(ProducaoEquipamentos producaoEquipamentos) {
		this.producaoEquipamentos = producaoEquipamentos;
	}

	public List<ProducaoEquipamentos> getList() {
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
