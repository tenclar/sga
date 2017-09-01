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
import br.gov.ac.seap.pga.model.CadeiaProdutiva;
import br.gov.ac.seap.pga.service.CadeiaProdutivaService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class CadeiaProdutivaController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CadeiaProdutivaService cadeiaprodutivaService;

	private CadeiaProdutiva cadeiaprodutiva;

	private List<CadeiaProdutiva> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	

	private String argumento;

	private String tipopesquisa = "nome";

	

	public CadeiaProdutivaController() {

	}

	

	

	public void actsalvar() {
		
		try {
			
			this.cadeiaprodutivaService.save(cadeiaprodutiva);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();
		CadeiaProdutiva c = new CadeiaProdutiva();
		c.setId(0L);
		c.setName("Selecione");
		for (CadeiaProdutiva p : this.cadeiaprodutivaService.findAll()) {
			retorno.add(new SelectItem(p, p.getName()));

		}
		return retorno;
	}
	
	public void actnovo() {
		this.cadeiaprodutiva = new CadeiaProdutiva();
		
		this.cadeiaprodutiva.setUser(super.getUserLogin());

		
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		cadeiaprodutiva = this.cadeiaprodutivaService.findById(cadeiaprodutiva.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.cadeiaprodutivaService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.cadeiaprodutiva = new CadeiaProdutiva();
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
				this.list = this.cadeiaprodutivaService.findListByNomeLike(argumento);
			}
			
			if ("descricao".equals(this.tipopesquisa)) {
				this.list = this.cadeiaprodutivaService.findListByDescricaoLike(argumento);
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

	public CadeiaProdutiva getCadeiaProdutiva() {
		return cadeiaprodutiva;
	}

	public void setCadeiaProdutiva(CadeiaProdutiva cadeiaprodutiva) {
		this.cadeiaprodutiva = cadeiaprodutiva;
	}

	public List<CadeiaProdutiva> getList() {
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
