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
import br.gov.ac.seap.pga.model.Estado;
import br.gov.ac.seap.pga.service.BairroService;
import br.gov.ac.seap.pga.service.CidadeService;
import br.gov.ac.seap.pga.service.EstadoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@ViewScoped
public class BairroController extends BaseController {

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

	private Bairro bairro;

	private Estado estadoselect;

	private List<Bairro> list;

	

	private List<Cidade> listaselectcidade;

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
		

		
		 if (bairro.getCidade().getEstado().getId() != 0L) {
		 this.listaselectcidade = this.cidadeService.findListByEstado(this.bairro.getCidade().getEstado());
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

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {

			this.bairro.setUser(getUser());
			this.bairroService.save(bairro);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlista();

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
		bairro = this.bairroService.findById(bairro.getId());
		this.handleEstadoChange();
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.bairroService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		// facesUtils = new FacesUtils();
		this.bairro = new Bairro();
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
				this.list = this.bairroService.findListByNomeLike(argumento);
			}
			if ("estado".equals(this.tipopesquisa)) {
				// this.list = this.bairroService.findListByEstado(argumento);
			}

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("search.not.found")
					+ e.getMessage());
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

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<Bairro> getList() {
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
