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

	private InsumoTrato insumoTrato;

	private List<InsumoTrato> list = null;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";



	public void actsalvar() {
		
		try {

			this.insumoTratoService.save(insumoTrato);
			this.actlimpa();
			FacesUtils.info(FacesUtils.mensages("message.save.success"));		

		} catch (Exception e) {			
			System.out.println(e.getMessage());
			FacesUtils.erro(FacesUtils.mensages("message.save.error"));
		}

	}

	public void actsalvarnext() {
		this.insumoTratoService.save(insumoTrato);
		FacesUtils.info(FacesUtils.mensages("message.save.success"));
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

	

	public void actlista() {
		list = this.insumoTratoService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.insumoTrato = null;	
		this.list = null;

		
	}

	public void actvolta() {
		actlimpa();
		actlista();

	}

	public DataModel<InsumoTrato> getLista(Producao p) {
		DataModel<InsumoTrato> dmLista = null;
		if (p != null) {
			if (p.getId() != null) {
				List<InsumoTrato> lista = this.insumoTratoService.findByProducao(p.getId());
				if (lista != null) {
					dmLista = new ListDataModel<InsumoTrato>(lista);
				}
			}
		}
		return dmLista;
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
