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
import br.gov.ac.seap.pga.model.ProducaoFomento;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.service.ProducaoFomentoService;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class ProducaoFomentoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProducaoFomentoService producaoFomentoService;

	@Autowired
	private ProducaoService producaoService;

	private ProducaoFomento producaoFomento;

	private List<ProducaoFomento> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";

	public ProducaoFomentoController() {

	}

	public void actsalvar() {
		
		try {

			this.producaoFomentoService.save(producaoFomento);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void actsalvarnext() {
		this.producaoFomentoService.save(producaoFomento);
		FacesUtils.info(FacesUtils.mensages("message.save.success"));
	}

	public void actnovo() {
		this.producaoFomento = new ProducaoFomento();
		this.producaoFomento.setUser(super.getUserLogin());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actnovo(Producao p) {
		this.actnovo();
		this.producaoFomento.setProducao(p);
	}

	public void acteditar() {
		producaoFomento = this.producaoFomentoService.findById(producaoFomento.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar(ProducaoFomento a) {

	}

	public void actlista() {
		list = this.producaoFomentoService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.producaoFomento = new ProducaoFomento();
		// this.argumento = new String();
		this.list = null;

		// FacesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	public DataModel<ProducaoFomento> getLista(Producao p) {
		DataModel<ProducaoFomento> dmLista = null;
		if (p != null) {
			if (p.getId() != null) {
				//System.out.println("id producao: " + p.getId());
				//Producao prod = this.producaoService.findById(p.getId());
				List<ProducaoFomento> lista = this.producaoFomentoService.findByProducao(p.getId());
				if (lista != null) {
					dmLista = new ListDataModel<ProducaoFomento>(lista);
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
	// \ this.list = this.producaoFomentoService.findListByNomeLike(argumento);
	// }
	// if ("description".equals(this.tipopesquisa)) {
	// this.list = this.producaoFomentoService.findListByDescriptionLike(argumento);
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

	public ProducaoFomento getProducaoFomento() {
		return producaoFomento;
	}

	public void setProducaoFomento(ProducaoFomento producaoFomento) {
		this.producaoFomento = producaoFomento;
	}

	public List<ProducaoFomento> getList() {
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
