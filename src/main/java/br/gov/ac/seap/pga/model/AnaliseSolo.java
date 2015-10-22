package br.gov.ac.seap.pga.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="analise_solo")
public class AnaliseSolo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar datacad;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar dataanalise;
	
	@NotNull
	private String local;
	
	@ManyToOne
	@JoinColumn(name="producao_id")
	private Producao producao;
	
	
	@NotNull
	@Column(name = "calcario", precision = 4, scale = 2) 
	private BigDecimal calcario;
	
	@NotNull
	@Column(name = "aplicado", precision = 4, scale = 2) 
	private BigDecimal aplicado;
	
	@NotNull
	@Column(name = "ureia", precision = 4, scale = 2) 
	private BigDecimal ureia;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Calendar getDataanalise() {
		return dataanalise;
	}

	public void setDataanalise(Calendar dataanalise) {
		this.dataanalise = dataanalise;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Producao getProducao() {
		return producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}

	public BigDecimal getCalcario() {
		return calcario;
	}

	public void setCalcario(BigDecimal calcario) {
		this.calcario = calcario;
	}

	public BigDecimal getAplicado() {
		return aplicado;
	}

	public void setAplicado(BigDecimal aplicado) {
		this.aplicado = aplicado;
	}

	public BigDecimal getUreia() {
		return ureia;
	}

	public void setUreia(BigDecimal ureia) {
		this.ureia = ureia;
	}
	
	
	
	
	

}
