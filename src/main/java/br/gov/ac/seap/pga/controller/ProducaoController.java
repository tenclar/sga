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
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.model.Propriedade;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@ViewScoped
public class ProducaoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProducaoService producaoService;

	private Producao producao;

	private List<Producao> list = null;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	

	private String argumento;

	private String tipopesquisa = "nome";

	

	public ProducaoController() {

	}

	

	

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {
			
			this.producaoService.save(producao);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public void actsalvarNext(){
		facesUtils = new FacesUtils();
		try {
			
			this.producaoService.save(producao);

			facesUtils.info(facesUtils.mensages("message.save.success"));
		

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	
	public void actnovo(Propriedade propriedade) {
		
		this.actnovo();
		this.producao.setPropriedade(propriedade);		
		
	}
	
	
	public void actnovo() {
		this.producao = new Producao();		
		this.producao.setUser(super.getUserLogin());

		
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		producao = this.producaoService.findById(producao.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.producaoService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		facesUtils = new FacesUtils();
		this.producao = new Producao();
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

		
			if ("description".equals(this.tipopesquisa)) {
				this.list = this.producaoService.findListByDescriptionLike(argumento);
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

	public Producao getProducao() {
		return producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}

	public List<Producao> getList() {
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
