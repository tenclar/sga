package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.Cidade;
import br.gov.ac.seap.pga.model.Authority;
import br.gov.ac.seap.pga.service.CidadeService;
import br.gov.ac.seap.pga.util.FacesUtils;


@ManagedBean
@ViewScoped
@Component
public class CidadeController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CidadeService cidadeService;

	private Cidade cidade;

	private List<Cidade> list;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private Authority authority;

	private String argumento;

	private String tipopesquisa = "nome";

	

	public CidadeController() {

	}

	
	public List<Cidade> getList() {
		List<Cidade> l =null;
		if (list != null){
			l = list;
		}
		return l;

	}
	

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {
			this.cidadeService.save(cidade);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlista();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (Cidade p : this.cidadeService.findAll()) {
			retorno.add(new SelectItem(p, p.getNome()));

		}
		return retorno;
	}
	

	public void actnovo() {
		this.cidade = new Cidade();
		this.cidade.setUser(super.getUserLogin());
		
		this.authority = new Authority();
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		cidade = this.cidadeService.findById(cidade.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.cidadeService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.cidade = new Cidade();
		this.list = null;
		this.argumento = new String();

		// facesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	


	public void actpesquisa() {
		facesUtils = new FacesUtils();
		
		try {

			if ("nome".equals(this.tipopesquisa)) {
				this.list = this.cidadeService.findListByNomeLike(argumento);
			}
			if ("estado".equals(this.tipopesquisa)) {
			//	this.list = this.cidadeService.findListByEstado(argumento);
			}

			if (list.isEmpty()) {
				actlimpa();
				throw new Exception();
			}
			
		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("search.not.found") + e.getMessage());
		}

	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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
