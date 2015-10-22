package br.gov.ac.seap.pga.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name="propriedade")
public class Propriedade implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	private String latitude;	
	
	private String longitude;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user = new User();
		
	
	
	@Column(name = "areatotal", precision = 4, scale = 2) 
	private BigDecimal areatotal;
	
	@ManyToOne
	@JoinColumn(name="produtor_id")
	private Produtor produtor  = new Produtor();
	
	@ManyToOne
	@JoinColumn(name="endereco_id")
	private Endereco endereco = new Endereco();	

	@ManyToOne
	@JoinColumn(name="ocupacao_id")
	private Ocupacao ocupacao = new Ocupacao();
	
	
	@ManyToOne
	@JoinColumn(name="unidademedida_id")
	private UnidadeMedida unidademedida;
	
	@OneToMany(mappedBy="propriedade", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Producao> producoes = new ArrayList<Producao>();
	
	

	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public Date getDatacad() {
		return datacad;
	}


	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}


	public BigDecimal getAreatotal() {
		return areatotal;
	}


	public void setAreatotal(BigDecimal areatotal) {
		this.areatotal = areatotal;
	}


	public Produtor getProdutor() {
		return produtor;
	}


	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Ocupacao getOcupacao() {
		return ocupacao;
	}


	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}


	public UnidadeMedida getUnidademedida() {
		return unidademedida;
	}


	public void setUnidademedida(UnidadeMedida unidademedida) {
		this.unidademedida = unidademedida;
	}


	

	
	

	public List<Producao> getProducoes() {
		return producoes;
	}


	public void setProducoes(List<Producao> producoes) {
		this.producoes = producoes;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	
	
	

}
