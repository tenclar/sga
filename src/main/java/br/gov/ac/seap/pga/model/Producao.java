package br.gov.ac.seap.pga.model;

import java.io.Serializable;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.gov.ac.seap.pga.enumerator.EnumState;


@Entity
@Table(name="producao")
public class Producao implements Serializable {
	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar datacad = Calendar.getInstance();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@NotNull
	private String descricao;	


	private int tempouso;
	
	
	
	@NotNull
	@Column(name = "area", precision = 4, scale = 2)
	private BigDecimal area;


	@Enumerated(EnumType.STRING)
	private  EnumState status = EnumState.ATIVO;
	
	@ManyToOne
	@JoinColumn(name="topografia_id")
	private Topografia topografia;
	
	@ManyToOne
	@JoinColumn(name="maodeobra_id")
	private MaoDeObra maodeobra;
	
	@ManyToOne
	@JoinColumn(name="tipopreparoarea_id")
	private TipoPreparoArea tipopreparoarea;

	@ManyToOne
	@JoinColumn(name="vegetacao_id")
	private Vegetacao vegetacao;
	
	
	@ManyToOne
	@JoinColumn(name="organizacaosocial_id")
	private OrganizacaoSocial organizacaosocial;	

	@ManyToOne
	@JoinColumn(name = "unidademedida_id")
	private UnidadeMedida unidademedida;

	@ManyToOne
	@JoinColumn(name = "propriedade_id")
	private Propriedade propriedade;

	@ManyToOne
	@JoinColumn(name = "cadeiaprodutiva_id")
	private CadeiaProdutiva cadeiaprodutiva;

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProducaoEquipamentos> equipamentos;

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PreparoArea> preparos;

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AnaliseSolo> analisesolos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDatacad() {
		return datacad;
	}

	public void setDatacad(Calendar datacad) {
		this.datacad = datacad;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	

	public EnumState getStatus() {
		return status;
	}

	public void setStatus(EnumState status) {
		this.status = status;
	}

	public TipoPreparoArea getTipopreparoarea() {
		return tipopreparoarea;
	}

	public void setTipopreparoarea(TipoPreparoArea tipopreparoarea) {
		this.tipopreparoarea = tipopreparoarea;
	}



	public MaoDeObra getMaodeobra() {
		return maodeobra;
	}

	public void setMaodeobra(MaoDeObra maodeobra) {
		this.maodeobra = maodeobra;
	}

	public Vegetacao getVegetacao() {
		return vegetacao;
	}

	public void setVegetacao(Vegetacao vegetacao) {
		this.vegetacao = vegetacao;
	}

	public int getTempouso() {
		return tempouso;
	}

	public void setTempouso(int tempouso) {
		this.tempouso = tempouso;
	}

	public OrganizacaoSocial getOrganizacaosocial() {
		return organizacaosocial;
	}

	public void setOrganizacaosocial(OrganizacaoSocial organizacaosocial) {
		this.organizacaosocial = organizacaosocial;
	}



	public UnidadeMedida getUnidademedida() {
		return unidademedida;
	}

	public void setUnidademedida(UnidadeMedida unidademedida) {
		this.unidademedida = unidademedida;
	}

	public Propriedade getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(Propriedade propriedade) {
		this.propriedade = propriedade;
	}

	public CadeiaProdutiva getCadeiaprodutiva() {
		return cadeiaprodutiva;
	}

	public void setCadeiaprodutiva(CadeiaProdutiva cadeiaprodutiva) {
		this.cadeiaprodutiva = cadeiaprodutiva;
	}

	public List<ProducaoEquipamentos> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<ProducaoEquipamentos> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public List<PreparoArea> getPreparos() {
		return preparos;
	}

	public void setPreparos(List<PreparoArea> preparos) {
		this.preparos = preparos;
	}

	public List<AnaliseSolo> getAnalisesolos() {
		return analisesolos;
	}

	public void setAnalisesolos(List<AnaliseSolo> analisesolos) {
		this.analisesolos = analisesolos;
	}
	
	
	
	public Topografia getTopografia() {
		return topografia;
	}

	public void setTopografia(Topografia topografia) {
		this.topografia = topografia;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
