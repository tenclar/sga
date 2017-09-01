package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.enumerator.EnumTipoPesquisa;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.model.Produtor;
import br.gov.ac.seap.pga.model.Propriedade;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.service.PropriedadeService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class ProducaoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProducaoService producaoService;
	
	@Autowired
	private PropriedadeService propriedadeService;

	private Producao producao;
	
	private Produtor produtor;
	
	private Propriedade propriedade; 

	private List<Producao> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	

	private String argumento;

	private String tipopesquisa = "nome";

	private EnumTipoPesquisa eTipoPesquisa = EnumTipoPesquisa.PRODUTOR;

//	private boolean pesquisanomeprodutor = true;
//	private boolean pesquisanomepropriedade = false;
//	private boolean pesquisacpf = false;		
//	private boolean pesquisacnpj = false;
//	private boolean pesquisacei = false;
	

	
	
	public void handleSelectPesquisa() {
		actlimpa();
		this.argumento = "";
		
//		if ("cpf".equals(this.tipopesquisa)) {
//			seteTipoPesquisa(EnumTipoPesquisa.CPF);
//			pesquisacpf = true;
//			pesquisacnpj = false;
//			pesquisanomeprodutor = false;
//			pesquisanomepropriedade = false;
//			
//
//		}
//		if ("cnpj".equals(this.tipopesquisa)) {
//			pesquisacnpj = true;
//			pesquisacpf = false;
//			pesquisanomeprodutor = false;
//			pesquisanomepropriedade = false;
//			
//
//		}
//
//		if ("nome".equals(this.tipopesquisa)) {
//			pesquisacpf = false;
//			pesquisanomeprodutor = false;
//			pesquisanomepropriedade = false;
//			pesquisacnpj = false;
//			this.argumento = new String();
//
//		}
		
	}

	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = null;
		try {
			retorno = new ArrayList<SelectItem>();
			for (Propriedade p : this.propriedadeService.findListByProdutorNomeLike(this.producao.getPropriedade().getProdutor().getName())) {
				retorno.add(new SelectItem(p, p.getNome()));
			}
			return retorno;

		} catch (Exception e) {
			return retorno;
		}
		
	}
	
	 public List<SelectItem> getSelectItemsTipoPesquisa() {
	        List<SelectItem> toReturn = new LinkedList<SelectItem>();
	        for (EnumTipoPesquisa c : EnumTipoPesquisa.values()) {
	            toReturn.add(new SelectItem(c.toString(), c.toString()));
	        }
	        return toReturn;
	    }
	

	public void actsalvar() {
		
		try {
			
			this.producaoService.save(producao);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	
	public void actsalvarNext(){
		
		try {
			
			this.producaoService.save(producao);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));
		

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	
	public void actnovo(Propriedade propriedade) {
		
		this.actnovo();
		this.producao.setPropriedade(propriedade);		
		
	}
	
	
	public void actnovo() {
		this.producao = new Producao();	
		this.producao.setPropriedade(new Propriedade());
		this.producao.getPropriedade().setProdutor(new Produtor());
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
		
		this.producao = new Producao();		
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

			if (isPesquisacpf()) {
				this.list = this.producaoService.findListByCpf(argumento);
			}
		
			if (isPesquisacei()) {
				this.list = this.producaoService.findListByCpf(argumento);
			}
			if (isPesquisacnpj()) {
				this.list = this.producaoService.findListByCpf(argumento);
			}
			
			if (isPesquisanomeprodutor()) {
				this.list = this.producaoService.findListByProdutorLike(argumento);
			}
			if (isPesquisanomepropriedade()) {
				this.list = this.producaoService.findListByPropriedadeLike(argumento);
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
	
	public boolean isPesquisacei() {
		return EnumTipoPesquisa.CEI.equals(eTipoPesquisa);
	}
	public boolean isPesquisacpf() {
		return EnumTipoPesquisa.CPF.equals(eTipoPesquisa);
	}
	public boolean isPesquisacnpj() {
		return EnumTipoPesquisa.CNPJ.equals(eTipoPesquisa);
	}
	public boolean isPesquisanomeprodutor() {
		return EnumTipoPesquisa.PRODUTOR.equals(eTipoPesquisa);
	}
	public boolean isPesquisanomepropriedade() {
		return EnumTipoPesquisa.PROPRIEDADE.equals(eTipoPesquisa);
	}
	
	public boolean isCadeiaProdutivaLeite(){
		return this.producao.getCadeiaprodutiva().getName().equals("LEITE");
	}
	public boolean isCadeiaProdutivaMamao(){
		return this.producao.getCadeiaprodutiva().getName().equals("MAMAO");
	}
	public boolean isCadeiaProdutivaBanana(){
		return this.producao.getCadeiaprodutiva().getName().equals("BANANA");
	}
	public boolean isCadeiaProdutivaMandioca(){
		return this.producao.getCadeiaprodutiva().getName().equals("MANDIOCA");
	}
	
	
	public EnumTipoPesquisa geteTipoPesquisa() {
		return eTipoPesquisa;
	}
	public void seteTipoPesquisa(EnumTipoPesquisa eTipoPesquisa) {
		this.eTipoPesquisa = eTipoPesquisa;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public Propriedade getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(Propriedade propriedade) {
		this.propriedade = propriedade;
	}
	
	
}
