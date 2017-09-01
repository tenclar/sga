package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.TipoInsumoTrato;
import br.gov.ac.seap.pga.service.TipoInsumoTratoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class TipoInsumoTratoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoInsumoTratoService tipoInsumoTratoService;

	private TipoInsumoTrato tipoInsumoTrato;

	private List<TipoInsumoTrato> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	

	private String argumento;

	private String tipopesquisa = "nome";

	

	public TipoInsumoTratoController() {

	}

	

	

	public void actsalvar() {
		
		try {
			
			this.tipoInsumoTratoService.save(tipoInsumoTrato);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (TipoInsumoTrato p : this.tipoInsumoTratoService.findAll()) {
			retorno.add(new SelectItem(p, p.getName()));

		}
		return retorno;
	}
	
	public void actnovo() {
		this.tipoInsumoTrato = new TipoInsumoTrato();
		
		this.tipoInsumoTrato.setUser(super.getUserLogin());

		
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		tipoInsumoTrato = this.tipoInsumoTratoService.findById(tipoInsumoTrato.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.tipoInsumoTratoService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.tipoInsumoTrato = new TipoInsumoTrato();
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
				this.list = this.tipoInsumoTratoService.findListByNomeLike(argumento);
			}
			if ("description".equals(this.tipopesquisa)) {
				this.list = this.tipoInsumoTratoService.findListByDescriptionLike(argumento);
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

	public TipoInsumoTrato getTipoInsumoTrato() {
		return tipoInsumoTrato;
	}

	public void setTipoInsumoTrato(TipoInsumoTrato tipoInsumoTrato) {
		this.tipoInsumoTrato = tipoInsumoTrato;
	}

	public List<TipoInsumoTrato> getList() {
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
