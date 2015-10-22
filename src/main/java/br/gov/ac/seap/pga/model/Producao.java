package br.gov.ac.seap.pga.model;

import java.io.Serializable;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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


@Entity
@Table(name="producao")
public class Producao implements Serializable {
	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar datacad;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@NotNull
	private String descricao;	
	/**
	 * já produsiu
	 * 
	 */
	@NotNull
	private boolean produziu;

	@NotNull	
	private int anos;
	
	@NotNull
	@Column(name = "area", precision = 4, scale = 2)
	private BigDecimal area;


	@NotNull
	private String status;
	
	
	private String maodeobra;
	

	/**
	 * campo , capoeira, mata , outros
	 * 
	 */
	private String vegetacao;
	
	private int tempouso;
	
	private OrganizacaoSocial organizacaosocial;


	@NotNull
	private String plantio;

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

	public boolean isProduziu() {
		return produziu;
	}

	public void setProduziu(boolean produziu) {
		this.produziu = produziu;
	}

	public int getAnos() {
		return anos;
	}

	public void setAnos(int anos) {
		this.anos = anos;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMaodeobra() {
		return maodeobra;
	}

	public void setMaodeobra(String maodeobra) {
		this.maodeobra = maodeobra;
	}

	public String getVegetacao() {
		return vegetacao;
	}

	public void setVegetacao(String vegetacao) {
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

	public String getPlantio() {
		return plantio;
	}

	public void setPlantio(String plantio) {
		this.plantio = plantio;
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
	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
