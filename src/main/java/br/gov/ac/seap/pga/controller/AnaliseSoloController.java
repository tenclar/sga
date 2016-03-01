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
import br.gov.ac.seap.pga.model.AnaliseSolo;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.service.AnaliseSoloService;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class AnaliseSoloController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AnaliseSoloService analiseSoloService;

	@Autowired
	private ProducaoService producaoService;

	private AnaliseSolo analiseSolo;

	private List<AnaliseSolo> list = null;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";

	public AnaliseSoloController() {

	}

	public void actsalvar() {
		facesUtils = new FacesUtils();
		try {

			this.analiseSoloService.save(analiseSolo);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			this.actlimpa();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void actsalvarnext() {
		this.analiseSoloService.save(analiseSolo);
		facesUtils.info(facesUtils.mensages("message.save.success"));
	}

	public void actnovo() {
		this.analiseSolo = new AnaliseSolo();
		this.analiseSolo.setUser(super.getUserLogin());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actnovo(Producao p) {
		this.actnovo();
		this.analiseSolo.setProducao(p);
	}

	public void acteditar() {
		analiseSolo = this.analiseSoloService.findById(analiseSolo.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar(AnaliseSolo a) {

	}

	public void actlista() {
		list = this.analiseSoloService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		facesUtils = new FacesUtils();
		this.analiseSolo = new AnaliseSolo();
		// this.argumento = new String();
		this.list = null;

		// facesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	public DataModel<AnaliseSolo> getLista(Producao p) {
		DataModel<AnaliseSolo> dmLista = null;
		if (p != null) {
			if (p.getId() != null) {
				//System.out.println("id producao: " + p.getId());
				Producao prod = this.producaoService.findById(p.getId());
				List<AnaliseSolo> lista = this.analiseSoloService.findByProducao(prod);
				if (lista != null) {
					dmLista = new ListDataModel<AnaliseSolo>(lista);
				}
			}
		}
		return dmLista;
	}

	// public void actpesquisa() {
	// facesUtils = new FacesUtils();
	// actlimpa();
	// try {
	//
	// if ("nome".equals(this.tipopesquisa)) {
	// \ this.list = this.analiseSoloService.findListByNomeLike(argumento);
	// }
	// if ("description".equals(this.tipopesquisa)) {
	// this.list = this.analiseSoloService.findListByDescriptionLike(argumento);
	// }
	//
	// if (list.isEmpty()) {
	// throw new Exception();
	// }
	// } catch (Exception e) {
	// facesUtils.aviso(facesUtils.mensages("search.not.found") +
	// e.getMessage());
	// }finally{
	//
	// }
	//
	// }

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

	public AnaliseSolo getAnaliseSolo() {
		return analiseSolo;
	}

	public void setAnaliseSolo(AnaliseSolo analiseSolo) {
		this.analiseSolo = analiseSolo;
	}

	public List<AnaliseSolo> getList() {
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
