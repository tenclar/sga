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
//import br.gov.ac.seap.pga.model.Colheita;
//import br.gov.ac.seap.pga.model.Producao;
//import br.gov.ac.seap.pga.service.ColheitaService;
//import br.gov.ac.seap.pga.service.ProducaoService;
//import br.gov.ac.seap.pga.util.FacesUtils;
//
//@Controller
//@ManagedBean
//@Scope("view")
//public class ColheitaController extends BaseController {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Autowired
//	private ColheitaService colheitaService;
//
//	@Autowired
//	private ProducaoService producaoService;
//
//	private Colheita colheita;
//
//	private List<Colheita> list = null;
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
//	public ColheitaController() {
//
//	}
//
//	public void actsalvar() {
//		
//		try {
//
//			this.colheitaService.save(colheita);
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
//		this.colheitaService.save(colheita);
//		FacesUtils.info(FacesUtils.mensages("message.save.success"));
//	}
//
//	public void actnovo() {
//		this.colheita = new Colheita();
//		this.colheita.setUser(super.getUserLogin());
//		this.setActionstate(EnumActionState.FORM);
//	}
//
//	public void actnovo(Producao p) {
//		this.actnovo();
//		this.colheita.setProducao(p);
//	}
//
//	public void acteditar() {
//		colheita = this.colheitaService.findById(colheita.getId());
//		this.setActionstate(EnumActionState.FORM);
//	}
//
//	public void acteditar(Colheita a) {
//
//	}
//
//	public void actlista() {
//		list = this.colheitaService.findAll();
//		this.setActionstate(EnumActionState.PESQUISA);
//	}
//
//	public void actlimpa() {
//		
//		this.colheita = new Colheita();
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
//	public DataModel<Colheita> getLista(Producao p) {
//		DataModel<Colheita> dmLista = null;
//		if (p != null) {
//			if (p.getId() != null) {
//				//System.out.println("id producao: " + p.getId());
//				//Producao prod = this.producaoService.findById(p.getId());
//				List<Colheita> lista = this.colheitaService.findByProducao(p.getId());
//				if (lista != null) {
//					dmLista = new ListDataModel<Colheita>(lista);
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
//	// \ this.list = this.colheitaService.findListByNomeLike(argumento);
//	// }
//	// if ("description".equals(this.tipopesquisa)) {
//	// this.list = this.colheitaService.findListByDescriptionLike(argumento);
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
//	public Colheita getColheita() {
//		return colheita;
//	}
//
//	public void setColheita(Colheita colheita) {
//		this.colheita = colheita;
//	}
//
//	public List<Colheita> getList() {
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
