package br.gov.ac.seap.pga.model;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@Table(name = "producao")
public class Producao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Temporal(TemporalType.DATE)
	private Calendar datacad = Calendar.getInstance();

	@ManyToOne
	@JoinColumn(name = "cadeiaprodutiva_id")
	private CadeiaProdutiva cadeiaprodutiva;

	@Column(name = "area", precision = 10, scale = 2)
	private double area;
	@Enumerated(EnumType.STRING)
	private EnumState status = EnumState.ATIVO;

	private String descricao;
	private int tempouso;

	@ManyToOne
	@JoinColumn(name = "topografia_id")
	private Topografia topografia;

	@ManyToOne
	@JoinColumn(name = "maodeobra_id")
	private MaoDeObra maodeobra;

	@ManyToOne
	@JoinColumn(name = "tipopreparoarea_id")
	private TipoPreparoArea tipopreparoarea;

	@ManyToOne
	@JoinColumn(name = "vegetacao_id")
	private Vegetacao vegetacao;

	@ManyToOne
	@JoinColumn(name = "organizacaosocial_id")
	private OrganizacaoSocial organizacaosocial;

	@ManyToOne
	@JoinColumn(name = "unidademedida_id")
	private UnidadeMedida unidademedida;

	@ManyToOne
	@JoinColumn(name = "propriedade_id")
	private Propriedade propriedade;

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProducaoEquipamentos> equipamentos = new ArrayList<ProducaoEquipamentos>();

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PreparoArea> preparos = new ArrayList<PreparoArea>();

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AnaliseSolo> analisesolos = new ArrayList<AnaliseSolo>();

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CadeiaLeite> cadeialeites = new ArrayList<CadeiaLeite>();

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<InsumoTrato> insumostratos = new ArrayList<InsumoTrato>();

	@OneToMany(mappedBy = "producao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProducaoFomento> producaoFomento;

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

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
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

	public List<InsumoTrato> getInsumostratos() {
		return insumostratos;
	}

	public void setInsumostratos(List<InsumoTrato> insumostratos) {
		this.insumostratos = insumostratos;
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

	public List<CadeiaLeite> getCadeialeites() {
		return cadeialeites;
	}

	public void setCadeialeites(List<CadeiaLeite> cadeialeites) {
		this.cadeialeites = cadeialeites;
	}

	public List<ProducaoFomento> getProducaoFomento() {
		return producaoFomento;
	}

	public void setProducaoFomento(List<ProducaoFomento> producaoFomento) {
		this.producaoFomento = producaoFomento;
	}

	/*
	 * CADEIA PRODUTIVA DE LEITE
	 */
	@Transient
	public double getTotalLeiteProducaoMensal() {
		double total = 0;
		for (CadeiaLeite c : cadeialeites) {
			total += c.getProducaoMensal();
		}
		return total;
	}

	@Transient
	public double getTotalLeiteProducaoDiaria() {
		double total = 0;
		for (CadeiaLeite c : cadeialeites) {
			total += c.getProducaodiaria();
		}
		return total;
	}

	@Transient
	public double getTotalLeiteProducaoHa() {
		double total = 0;
		for (CadeiaLeite c : cadeialeites) {
			total += c.getProducaoHa();
		}
		return total;
	}

	@Transient
	public double getTotalLeiteProducaoMedia() {
		double total = 0;
		for (CadeiaLeite c : cadeialeites) {
			total += c.getProducaoMedia();
		}
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producao other = (Producao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
