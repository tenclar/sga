package br.gov.ac.seap.pga.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datacad = Calendar.getInstance();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataanalise;
	
	@NotNull
	private String local;
	
	@ManyToOne
	@JoinColumn(name="producao_id")
	private Producao producao = new Producao();
	
	
	@ManyToOne
	@JoinColumn(name="tipoadubo_id")
	private TipoAdubo tipoadubo = new TipoAdubo();
	
	@NotNull
	@Column(name = "quantidae", precision = 10, scale = 2) 
	private BigDecimal quantidade;
	
	
	@NotNull
	@Column(name = "aplicado", precision = 10, scale = 2) 
	private BigDecimal aplicado;
	
	

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

	public Date getDataanalise() {
		return dataanalise;
	}

	public void setDataanalise(Date dataanalise) {
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

	

	public BigDecimal getAplicado() {
		return aplicado;
	}

	public void setAplicado(BigDecimal aplicado) {
		this.aplicado = aplicado;
	}

	public TipoAdubo getTipoadubo() {
		return tipoadubo;
	}

	public void setTipoadubo(TipoAdubo tipoadubo) {
		this.tipoadubo = tipoadubo;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
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
		AnaliseSolo other = (AnaliseSolo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
	
	

}
