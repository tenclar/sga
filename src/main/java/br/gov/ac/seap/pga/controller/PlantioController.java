//package br.gov.ac.seap.pga.controller;
//
//import java.util.List;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.component.UIForm;
//import javax.faces.model.DataModel;
//import javax.faces.model.ListDataModel;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
//import br.gov.ac.seap.pga.enumerator.EnumActionState;
//import br.gov.ac.seap.pga.model.Plantio;
//import br.gov.ac.seap.pga.model.Producao;
//import br.gov.ac.seap.pga.service.PlantioService;
//import br.gov.ac.seap.pga.service.ProducaoService;
//import br.gov.ac.seap.pga.util.FacesUtils;
//
//@Controller
//@ManagedBean
//@Scope("view")
//public class PlantioController extends BaseController {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Autowired
//	private PlantioService plantioService;
//
//	@Autowired
//	private ProducaoService producaoService;
//
//	private Plantio plantio;
//
//	private List<Plantio> list = null;
//
//	
//
//	private EnumActionState actionstate = EnumActionState.PESQUISA;
//
//	private UIForm form;
//
//	private String argumento;
//
//	private String tipopesquisa = "nome";
//
//	public PlantioController() {
//
//	}
//
//	public void actsalvar() {
//		
//		try {
//
//			this.plantioService.save(plantio);
//
//			FacesUtils.info(FacesUtils.mensages("message.save.success"));
//
//			this.actlimpa();
//
//		} catch (Exception e) {
//			FacesUtils.erro(FacesUtils.mensages("message.save.error") + e.getMessage());
//			System.out.println(e.getMessage());
//		}
//
//	}
//
//	public void actsalvarnext() {
//		this.plantioService.save(plantio);
//		FacesUtils.info(FacesUtils.mensages("message.save.success"));
//	}
//
//	public void actnovo() {
//		this.plantio = new Plantio();
//		this.plantio.setUser(super.getUserLogin());
//		this.setActionstate(EnumActionState.FORM);
//	}
//
//	public void actnovo(Producao p) {
//		this.actnovo();
//		this.plantio.setProducao(p);
//	}
//
//	public void acteditar() {
//		plantio = this.plantioService.findById(plantio.getId());
//		this.setActionstate(EnumActionState.FORM);
//	}
//
//	public void acteditar(Plantio a) {
//
//	}
//
//	public void actlista() {
//		list = this.plantioService.findAll();
//		this.setActionstate(EnumActionState.PESQUISA);
//	}
//
//	public void actlimpa() {
//
//		this.plantio = new Plantio();
//		// this.argumento = new String();
//		this.list = null;
//
//		// FacesUtils.cleanSubmittedValues(form);
//	}
//
//	public void actvolta() {
//		actlimpa();
//		actlista();
//		// this.setActionstate(EnumActionState.PESQUISA);
//	}
//
//	public DataModel<Plantio> getLista(Producao p) {
//		DataModel<Plantio> dmLista = null;
//		if (p != null) {
//			if (p.getId() != null) {
//				//System.out.println("id producao: " + p.getId());
//				//Producao prod = this.producaoService.findById(p.getId());
//				List<Plantio> lista = this.plantioService.findByProducao(p.getId());
//				if (lista != null) {
//					dmLista = new ListDataModel<Plantio>(lista);
//				}
//			}
//		}
//		return dmLista;
//	}
//
//	// public void actpesquisa() {
//	// 
//	// actlimpa();
//	// try {
//	//
//	// if ("nome".equals(this.tipopesquisa)) {
//	// \ this.list = this.plantioService.findListByNomeLike(argumento);
//	// }
//	// if ("description".equals(this.tipopesquisa)) {
//	// this.list = this.plantioService.findListByDescriptionLike(argumento);
//	// }
//	//
//	// if (list.isEmpty()) {
//	// throw new Exception();
//	// }
//	// } catch (Exception e) {
//	// FacesUtils.aviso(FacesUtils.mensages("search.not.found") +
//	// e.getMessage());
//	// }finally{
//	//
//	// }
//	//
//	// }
//
//	public String getTipopesquisa() {
//		return tipopesquisa;
//	}
//
//	public void setTipopesquisa(String tipopesquisa) {
//		this.tipopesquisa = tipopesquisa;
//	}
//
//	public boolean isPesquisando() {
//		return EnumActionState.PESQUISA.equals(actionstate);
//	}
//
//	public boolean isAddEdit() {
//		return EnumActionState.FORM.equals(actionstate);
//	}
//
//	public Plantio getPlantio() {
//		return plantio;
//	}
//
//	public void setPlantio(Plantio plantio) {
//		this.plantio = plantio;
//	}
//
//	public List<Plantio> getList() {
//		return list;
//
//	}
//
//	public void setActionstate(EnumActionState actionstate) {
//		this.actionstate = actionstate;
//	}
//
//	public UIForm getForm() {
//		return form;
//	}
//
//	public void setForm(UIForm form) {
//		this.form = form;
//	}
//
//	public String getArgumento() {
//		return argumento;
//	}
//
//	public void setArgumento(String argumento) {
//		this.argumento = argumento;
//	}
//
//}
