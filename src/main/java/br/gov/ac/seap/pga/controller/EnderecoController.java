package br.gov.ac.seap.pga.controller;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.Bairro;
import br.gov.ac.seap.pga.model.Cidade;
import br.gov.ac.seap.pga.model.Endereco;
import br.gov.ac.seap.pga.model.Estado;
import br.gov.ac.seap.pga.service.BairroService;
import br.gov.ac.seap.pga.service.CidadeService;
import br.gov.ac.seap.pga.service.EnderecoService;
import br.gov.ac.seap.pga.service.EstadoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@ViewScoped
public class EnderecoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;
	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private EnderecoService enderecoService;

	
	

	private Estado estadoselect;
	private Cidade cidadeselect;
	private Bairro bairroselect;
	private Endereco endereco;

	private List<Endereco> list;

	private List<Cidade> listaselectcidade;
	private List<Bairro> listaselectbairro;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";

	public List<SelectItem> getSelectItemsEstado() {
		List<SelectItem> toReturn = new LinkedList<SelectItem>();

		Estado est = new Estado();
		est.setId(0L);
		est.setNome("Selecione");
		toReturn.add(new SelectItem(est, est.getNome()));

		for (Estado uf : this.estadoService.findAllOrderBySigla()) {

			toReturn.add(new SelectItem(uf, uf.getSigla()));

		}
		return toReturn;

	}

	public void handleEstadoChange() {
		

		
		 if (estadoselect.getId() != 0L) {
		 this.listaselectcidade = this.cidadeService.findListByEstado(this.estadoselect);
		 } else {
		 this.listaselectcidade = null;
		 this.getSelectItemsCidade().clear();
		 }

	}

	public List<SelectItem> getSelectItemsCidade() {
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		Cidade c = new Cidade();
		c.setId(0L);
		c.setNome("Selecione");
		toReturn.add(new SelectItem(c, c.getNome(), null, false, true, true));
		if (listaselectcidade != null) {
			for (Cidade cid : listaselectcidade) {
				toReturn.add(new SelectItem(cid, cid.getNome()));
			}
		}
		return toReturn;
	}
	
	public void handleCidadeChange() {
		

		
		 if (cidadeselect.getId() != 0L) {
		 this.listaselectbairro = this.bairroService.findListByCidade(this.cidadeselect);
		 } else {
		 this.listaselectbairro = null;
		 this.getSelectItemsBairro().clear();
		 }

	}
	public List<SelectItem> getSelectItemsBairro() {
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		Bairro b = new Bairro();
		b.setId(0L);
		b.setNome("Selecione");
		toReturn.add(new SelectItem(b, b.getNome(), null, false, true, true));
		if (listaselectbairro != null) {
			for (Bairro x : listaselectbairro) {
				toReturn.add(new SelectItem(x, x.getNome()));
			}
		}
		return toReturn;
	}

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {

			this.endereco.setUser(getUser());
			this.enderecoService.save(endereco);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error")
					+ e.getMessage());
		}

	}

	public void actnovo() {
		this.actlimpa();

		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar() {
		endereco = this.enderecoService.findById(endereco.getId());
		this.handleEstadoChange();
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.enderecoService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		// facesUtils = new FacesUtils();
		this.endereco = new Endereco();
		this.estadoselect = new Estado();
		this.listaselectcidade = null;
		
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
				this.list = this.enderecoService.findListByLogradouroLike(argumento);
			}			

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("search.not.found")	);
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

	

	public Bairro getBairroselect() {
		return bairroselect;
	}

	public void setBairroselect(Bairro bairroselect) {
		this.bairroselect = bairroselect;
	}

	
	
	public Cidade getCidadeselect() {
		return cidadeselect;
	}

	public void setCidadeselect(Cidade cidadeselect) {
		this.cidadeselect = cidadeselect;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Endereco> getList() {
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

	public Estado getEstadoselect() {
		return estadoselect;
	}

	public void setEstadoselect(Estado estadoselect) {
		this.estadoselect = estadoselect;
	}

}
