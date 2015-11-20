package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.enumerator.EnumTipoPessoa;
import br.gov.ac.seap.pga.model.Produtor;
import br.gov.ac.seap.pga.model.Propriedade;
import br.gov.ac.seap.pga.service.ProdutorService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class ProdutorController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutorService produtorService;

	private Produtor produtor;

	private Propriedade propriedade;

	private List<Produtor> list = null;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "cpf";

	private boolean tipopessoafisica = true;
	private boolean tipopessoajuridica = false;
	private boolean pesquisanome = false;

	private boolean pesquisacpf = true;

	private boolean pesquisacnpj = false;

	private boolean novocadastro = false;

	private boolean duplicado;

	public ProdutorController() {

	}

	public void handleSelectPesquisa() {
		actlimpa();
		if ("cpf".equals(this.tipopesquisa)) {
			pesquisacpf = true;
			pesquisacnpj = false;
			pesquisanome = false;
			this.argumento = new String();

		}
		if ("cnpj".equals(this.tipopesquisa)) {
			pesquisacnpj = true;
			pesquisacpf = false;
			pesquisanome = false;
			this.argumento = new String();

		}

		if ("nome".equals(this.tipopesquisa)) {
			pesquisacpf = false;
			pesquisanome = true;
			pesquisacnpj = false;
			this.argumento = new String();

		}
		
	}

	public void handleSelectTipoPessoa() {

		if (EnumTipoPessoa.FISICA.equals(this.produtor.getTipopessoa())) {
			tipopessoafisica = true;
			tipopessoajuridica = false;

		}

		if (EnumTipoPessoa.JURIDICA.equals(this.produtor.getTipopessoa())) {
			tipopessoafisica = false;
			tipopessoajuridica = true;

		}

	}
	
	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (Produtor p : this.produtorService.findAll()) {
			retorno.add(new SelectItem(p, p.getName()));

		}
		return retorno;
	}

	public List<SelectItem> getSelectItemsEnumTipoPessoa() {
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for (EnumTipoPessoa c : EnumTipoPessoa.values()) {
			toReturn.add(new SelectItem(c.toString(), c.toString()));
		}
		return toReturn;
	}

	public void actSalvarNew() {
		facesUtils = new FacesUtils();
		try {
			if (this.produtorService.findByCpf(this.produtor.getCpf()) == null) {
				this.duplicado = false;

			} else {
				this.duplicado = true;
				facesUtils.aviso("Inclusão não efetudada  CPF / CNPJ: " + this.produtor.getCpf() + " Já Existe no cadatro ");
			}
			
			//RequestContext rc = RequestContext.getCurrentInstance();
			//rc.addCallbackParam("duplicado", duplicado);
			facesUtils.addCallback("duplicado", duplicado);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {

			this.produtorService.save(produtor);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlista();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void actaddprop() {
		facesUtils = new FacesUtils();
		try {
			
			this.produtor.getPropriedades().add(propriedade);
			this.produtorService.save(produtor);
			facesUtils.info(facesUtils.mensages("message.save.success"));

		

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	

	public void actnovo() {
		this.produtor = new Produtor();

		this.produtor.setUser(getUserLogin());
		this.setActionstate(EnumActionState.FORM);
		this.handleSelectTipoPessoa();
	}

	public void actnovoprop() {
		this.propriedade = new Propriedade();
		this.propriedade.setUser(getUserLogin());
		this.propriedade.setProdutor(produtor);
	}

	public void acteditar() {
		produtor = this.produtorService.findById(produtor.getId());
		this.setActionstate(EnumActionState.FORM);
	}
	public void acteditarprop() {
		produtor = this.produtorService.findById(produtor.getId());
		this.setActionstate(EnumActionState.FORM);
	}
	
	

	public void actlista() {
		list = this.produtorService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		facesUtils = new FacesUtils();
		this.novocadastro = false;
		this.produtor = new Produtor();
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
				this.list = this.produtorService.findListByNameLike(argumento);
			}
			if ("cpf".equals(this.tipopesquisa)) {
				this.list = this.produtorService.findListByCpf(argumento);
			}
			if ("cnpj".equals(this.tipopesquisa)) {
				this.list = this.produtorService.findListByCpf(argumento);
			}

			if (list.isEmpty()) {
				novocadastro = true;
				throw new Exception();
			}
		} catch (Exception e) {
			facesUtils.aviso(facesUtils.mensages("search.not.found"));
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

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public List<Produtor> getList() {
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

	public boolean isTipopessoafisica() {
		return tipopessoafisica;
	}

	public boolean isTipopessoajuridica() {
		return tipopessoajuridica;
	}

	public boolean isPesquisacpf() {
		return pesquisacpf;
	}

	public boolean isPesquisacnpj() {
		return pesquisacnpj;
	}

	public boolean isNovocadastro() {
		return novocadastro;
	}

	public boolean isPesquisanome() {
		return pesquisanome;
	}

	public Propriedade getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(Propriedade propriedade) {
		this.propriedade = propriedade;
	}

}
