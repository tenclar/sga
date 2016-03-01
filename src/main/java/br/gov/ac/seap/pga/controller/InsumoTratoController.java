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
import br.gov.ac.seap.pga.model.InsumoTrato;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.service.InsumoTratoService;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class InsumoTratoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private InsumoTratoService insumoTratoService;

	@Autowired
	private ProducaoService producaoService;

	private InsumoTrato insumoTrato;

	private List<InsumoTrato> list = null;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";

	public InsumoTratoController() {

	}

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {

			this.insumoTratoService.save(insumoTrato);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void actsalvarnext() {
		this.insumoTratoService.save(insumoTrato);
		facesUtils.info(facesUtils.mensages("message.save.success"));
	}

	public void actnovo() {
		this.insumoTrato = new InsumoTrato();
		this.insumoTrato.setUser(super.getUserLogin());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actnovo(Producao p) {
		this.actnovo();
		this.insumoTrato.setProducao(p);
	}

	public void acteditar() {
		insumoTrato = this.insumoTratoService.findById(insumoTrato.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar(InsumoTrato a) {

	}

	public void actlista() {
		list = this.insumoTratoService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		facesUtils = new FacesUtils();
		this.insumoTrato = new InsumoTrato();
		// this.argumento = new String();
		this.list = null;

		// facesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	public DataModel<InsumoTrato> getLista(Producao p) {
		DataModel<InsumoTrato> dmLista = null;
		if (p != null) {
			if (p.getId() != null) {
				//System.out.println("id producao: " + p.getId());
				Producao prod = this.producaoService.findById(p.getId());
				List<InsumoTrato> lista = this.insumoTratoService.findByProducao(prod);
				if (lista != null) {
					dmLista = new ListDataModel<InsumoTrato>(lista);
				}
			}
		}
		return dmLista;
	}

	// public void actpesquisa() {
	// facesUtils = new FacesUtils();
	// actlimpa();
	// try {
	//
	// if ("nome".equals(this.tipopesquisa)) {
	// \ this.list = this.insumoTratoService.findListByNomeLike(argumento);
	// }
	// if ("description".equals(this.tipopesquisa)) {
	// this.list = this.insumoTratoService.findListByDescriptionLike(argumento);
	// }
	//
	// if (list.isEmpty()) {
	// throw new Exception();
	// }
	// } catch (Exception e) {
	// facesUtils.aviso(facesUtils.mensages("search.not.found") +
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

	public InsumoTrato getInsumoTrato() {
		return insumoTrato;
	}

	public void setInsumoTrato(InsumoTrato insumoTrato) {
		this.insumoTrato = insumoTrato;
	}

	public List<InsumoTrato> getList() {
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
