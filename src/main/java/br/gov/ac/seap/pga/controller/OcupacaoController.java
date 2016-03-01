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
import br.gov.ac.seap.pga.model.Ocupacao;
import br.gov.ac.seap.pga.service.OcupacaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@ViewScoped
public class OcupacaoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OcupacaoService ocupacaoService;

	private Ocupacao ocupacao;

	private List<Ocupacao> list = null;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	

	private String argumento;

	private String tipopesquisa = "nome";

	

	public OcupacaoController() {

	}

	

	

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {
			
			this.ocupacaoService.save(ocupacao);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (Ocupacao p : this.ocupacaoService.findAll()) {
			retorno.add(new SelectItem(p, p.getName()));

		}
		return retorno;
	}
	
	public void actnovo() {
		this.ocupacao = new Ocupacao();		
		this.ocupacao.setUser(super.getUserLogin());		
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		ocupacao = this.ocupacaoService.findById(ocupacao.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.ocupacaoService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		facesUtils = new FacesUtils();
		this.ocupacao = new Ocupacao();
		//this.argumento = new String();
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
				this.list = this.ocupacaoService.findListByNomeLike(argumento);
			}
			

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			facesUtils.aviso(facesUtils.mensages("search.not.found") + e.getMessage());
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

	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}

	public List<Ocupacao> getList() {
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
