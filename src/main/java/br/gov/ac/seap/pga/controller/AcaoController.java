package br.gov.ac.seap.pga.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.Acao;
import br.gov.ac.seap.pga.model.Producao;
import br.gov.ac.seap.pga.model.Produtor;
import br.gov.ac.seap.pga.model.Propriedade;
import br.gov.ac.seap.pga.service.AcaoService;
import br.gov.ac.seap.pga.service.ProducaoService;
import br.gov.ac.seap.pga.service.ProdutorService;
import br.gov.ac.seap.pga.service.PropriedadeService;
import br.gov.ac.seap.pga.util.FacesUtils;


@ManagedBean
@Controller
@Scope("view")
public class AcaoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AcaoService acaoService;
	

	@Autowired
	private ProdutorService produtorService;
	

	@Autowired
	private PropriedadeService propriedadeService;
	
	@Autowired 
	private ProducaoService producaoService;
		
	private Acao acao;

	private List<Acao> list;	
	
	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private String argumento;

	private String tipopesquisa = "nome";

	private ScheduleModel eventModel;
	
	private List<Propriedade> listpropriedades=null;

	private List<Producao> listproducoes = null;

	

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();

		
		actlista();
		for (Acao a : list) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			
			evt.setData(a.getId());
			evt.setDescription(a.getDescription());
			evt.setTitle(a.getTitulo());
			evt.setStartDate(a.getInicio());
			evt.setEndDate(a.getFim());
			evt.setAllDay(a.getDiatodo());			
			evt.setEditable(true);			
			if(a.isStatus()){
				evt.setStyleClass("actives");
			}else evt.setStyleClass("warnings");

			eventModel.addEvent(evt);

		}
	}

	public void onEventSelect(SelectEvent selectEvent) {
		//System.out.println("Selecionado Ação");		
		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();			
			if(event !=null){
			this.actnovo();	
			acteditar((Long)event.getData());
			}		
	}

	public void onDateSelect(SelectEvent selectEvent) {
		//System.out.println("Selecionado nova data");
		this.actnovo();
		ScheduleEvent ev  = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		
		//this.acao.setDescription(event.getDescription());
		//this.acao.setTitulo(event.getTitle());
		this.acao.setInicio(ev.getStartDate());
		this.acao.setFim(ev.getEndDate());

	}

	public void onEventMove(ScheduleEntryMoveEvent event) {

		String mensagem = "Acão movida " + " Dia(s):" + event.getDayDelta() + ", Minuto(s):" + event.getMinuteDelta();	
		FacesUtils.info(mensagem);

	}

	
	public void onEventResize(ScheduleEntryResizeEvent event) {

		String mensagem = "Acão movida " + " Dia(s):" + event.getDayDelta() + ", Minuto(s):" + event.getMinuteDelta();	
		FacesUtils.info(mensagem);
	}

	public List<SelectItem> getSelectItems() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		for (Acao p : this.acaoService.findAll()) {
			retorno.add(new SelectItem(p, p.getDescription()));

		}
		return retorno;
	}

	public void selecionaProdutor(){
		Produtor produtor = null;
		if(this.acao != null){			
			produtor = produtorService.findById(this.acao.getProducao().getPropriedade().getProdutor().getId());					
			 listpropriedades = propriedadeService.findByProdutorId(produtor.getId());			 
			}
	}
	public void handleListaProducao(){
		
		if(this.acao != null){
		
			if(this.acao.getProducao().getPropriedade().getId()!=0L){			
				listproducoes = producaoService.findListByPropriedadeId(this.acao.getProducao().getPropriedade().getId());
				}
				else{
					listproducoes= null;					
					this.getSelectItemsProducao().clear();
				}
			}
	}
	public List<SelectItem> getSelectItemsPropriedade() {
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		Propriedade p = new Propriedade();
		p.setId(0L);
		p.setNome("Selecione");
		toReturn.add(new SelectItem(p, p.getNome() , null, false, true, true));
		if (listpropriedades != null) {
			for (Propriedade prop : listpropriedades) {
				toReturn.add(new SelectItem(prop, prop.getNome()));
			}
		}	
		
		return toReturn;
	}
	
	public List<SelectItem> getSelectItemsProducao() {
		List<SelectItem> toReturn = new LinkedList<SelectItem>();	
		Producao p = new Producao();
		p.setId(0L);
		p.setDescricao("Selecione");
		toReturn.add(new SelectItem(p, p.getDescricao(), null, false, true, true));
		
			if (listproducoes  != null) {
				for (Producao prod : listproducoes) {
				toReturn.add(new SelectItem(prod, prod.getCadeiaprodutiva().getName()));
				}
			}	
		
		return toReturn;
	}
	public void actsalvar(ActionEvent actionEvent) {
		
		try {
			
			this.acaoService.save(acao);
			this.init();

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

			

		} catch (Exception e) {
			// FacesUtils.erro(FacesUtils.mensages("message.save.error") +
			// e.getMessage());
			// System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actnovo() {
		this.setActionstate(EnumActionState.FORM);
		this.acao = new Acao();	
		this.acao.setProducao(new Producao());
		this.acao.getProducao().setPropriedade(new Propriedade());
		this.acao.getProducao().getPropriedade().setProdutor(new Produtor());
		this.acao.setUser(super.getUserLogin());

	}

	public void acteditar(Long id) {
		acao = this.acaoService.findById(id);
		this.setActionstate(EnumActionState.FORM);
	}

	public void actlista() {
		list = this.acaoService.findAcoes();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	public void actlimpa() {
		
		this.acao = null;
		this.list = null;
		// this.argumento = new String();
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

			if ("nome".equals(this.tipopesquisa)) {
				this.list = this.acaoService.findListByDescriptionLike(argumento);
			}

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			FacesUtils.aviso(FacesUtils.mensages("search.not.found") + e.getMessage());
		} finally {

		}

	}

	public ScheduleModel getEventModel() {
		//this.init();
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
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
	
	

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public List<Acao> getList() {
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
