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
import br.gov.ac.seap.pga.model.CadeiaLeite;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.service.CadeiaLeiteService;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@Scope("view")
public class CadeiaLeiteController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CadeiaLeiteService cadeiaLeiteService;

	@Autowired
	private ProducaoService producaoService;

	private CadeiaLeite cadeiaLeite;

	private List<CadeiaLeite> list = null;

	

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	

	private String argumento;

	private String tipopesquisa = "nome";

	public CadeiaLeiteController() {

	}

	public void actsalvar() {
		
		try {

			this.cadeiaLeiteService.save(cadeiaLeite);
			this.actlimpa();
			FacesUtils.info(FacesUtils.mensages("message.save.success"));		

		} catch (Exception e) {			
			System.out.println(e.getMessage());
			FacesUtils.erro(FacesUtils.mensages("message.save.error"));
		}

	}

	public void actsalvarnext() {
		this.cadeiaLeiteService.save(cadeiaLeite);
		FacesUtils.info(FacesUtils.mensages("message.save.success"));
	}

	public void actnovo() {
		this.cadeiaLeite = new CadeiaLeite();
		this.cadeiaLeite.setUser(super.getUserLogin());
		this.setActionstate(EnumActionState.FORM);
	}

	public void actnovo(Producao p) {
		this.actnovo();
		this.cadeiaLeite.setProducao(p);
	}

	public void acteditar() {
		cadeiaLeite = this.cadeiaLeiteService.findById(cadeiaLeite.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void acteditar(CadeiaLeite a) {

	}

	public void actlista() {
		list = this.cadeiaLeiteService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {

		this.cadeiaLeite = new CadeiaLeite();
		// this.argumento = new String();
		this.list = null;

		// FacesUtils.cleanSubmittedValues(form);
	}

	public void actvolta() {
		actlimpa();
		actlista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	public DataModel<CadeiaLeite> getLista(Producao p) {
		DataModel<CadeiaLeite> dmLista = null;
		if (p != null) {
			if (p.getId() != null) {
				//System.out.println("id producao: " + p.getId());
				///Producao prod = this.producaoService.findById(p.getId());
				List<CadeiaLeite> lista = this.cadeiaLeiteService.findByProducao(p.getId());
				if (lista != null) {
					dmLista = new ListDataModel<CadeiaLeite>(lista);
				}
			}
		}
		return dmLista;
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

	public CadeiaLeite getCadeiaLeite() {
		return cadeiaLeite;
	}

	public void setCadeiaLeite(CadeiaLeite cadeiaLeite) {
		this.cadeiaLeite = cadeiaLeite;
	}

	public List<CadeiaLeite> getList() {
		return list;

	}

	public void setActionstate(EnumActionState actionstate) {
		this.actionstate = actionstate;
	}

	



	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

}
