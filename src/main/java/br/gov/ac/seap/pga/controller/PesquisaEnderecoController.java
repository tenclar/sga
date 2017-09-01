package br.gov.ac.seap.pga.controller;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
@Scope("view")
public class PesquisaEnderecoController extends BaseController {

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

	private Estado estado;

	private Cidade cidade;

	private Bairro bairro;

	private Endereco endereco;

	private List<Cidade> listaselectcidade = null;

	private List<Endereco> list = null;

	private List<Bairro> listaselectbairro = null;

	

	private UIForm form;

	private String argumento;

	private String tipopesquisa = "nome";

	public PesquisaEnderecoController() {

	}

	public void actsalvar() {
		
		try {

			this.estadoService.save(estado);

			FacesUtils.info(FacesUtils.mensages("message.save.success"));

		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("message.save.error")
					+ e.getMessage());
			System.out.println(e.getMessage());
		}

	}

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

		if (estado.getId() != 0L) {
			this.listaselectcidade = this.cidadeService
					.findListByEstado(this.estado);
		} else {
			this.listaselectcidade = null;
			this.listaselectbairro = null;
			this.getSelectItemsCidade().clear();
			this.getSelectItemsBairro().clear();

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

	public void handlePesquisaCidadeChange() {

		if (cidade.getId() != 0L) {
			this.listaselectbairro = this.bairroService
					.findListByCidade(this.cidade);
		} else {
			listaselectbairro = null;
			this.getSelectItemsBairro().clear();
		}

	}

	public List<SelectItem> getSelectItemsBairro() {
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		Bairro b = new Bairro();
		b.setId(0L);
		b.setNome("Todos");
		toReturn.add(new SelectItem(b, b.getNome()));

		if (listaselectbairro != null) {
			for (Bairro ba : listaselectbairro) {
				toReturn.add(new SelectItem(ba, ba.getNome()));
			}
		}
		return toReturn;
	}

	public void actlimpa() {

		this.estado = new Estado();
		this.cidade = new Cidade();
		this.bairro = new Bairro();
		this.endereco = new Endereco();

		this.listaselectcidade = null;
		this.listaselectbairro = null;
		this.list = null;
		this.getSelectItemsCidade().clear();
		this.getSelectItemsBairro().clear();
		this.argumento = new String();

	}

	public void actvolta() {
		actlimpa();

	}

	public void actpesquisa() {
		

		
		try {

			if (this.bairro.getId() == 0L) {
				this.list = this.enderecoService
						.findListByCidadeAndLogradouroLike(this.cidade.getId(),
								argumento);
			} else {
				this.list = this.enderecoService
						.findListByBairroAndLogradouroLike(bairro, argumento);
			}

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			FacesUtils.erro(FacesUtils.mensages("search.not.found")	+ e.getMessage());
		}

	}

	public String getTipopesquisa() {
		return tipopesquisa;
	}

	public void setTipopesquisa(String tipopesquisa) {
		this.tipopesquisa = tipopesquisa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Endereco> getList() {
		return list;

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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
