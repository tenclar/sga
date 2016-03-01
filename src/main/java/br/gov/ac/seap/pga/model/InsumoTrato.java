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
@Table(name="insumotrato")
public class InsumoTrato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar datacad = Calendar.getInstance();
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar data_info;
	
	@ManyToOne
	@JoinColumn(name="producao_id")
	private Producao producao;
	
	@ManyToOne
	@JoinColumn(name="tipoinsumotrato_id")
	private TipoInsumoTrato tipoInsumoTrato;

	
	@NotNull
	@Column(name = "valor", precision = 4, scale = 2) 
	private BigDecimal valor;


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


	public Calendar getData_info() {
		return data_info;
	}


	public void setData_info(Calendar data_info) {
		this.data_info = data_info;
	}


	public Producao getProducao() {
		return producao;
	}


	public void setProducao(Producao producao) {
		this.producao = producao;
	}


	public TipoInsumoTrato getTipoInsumoTrato() {
		return tipoInsumoTrato;
	}


	public void setTipoInsumoTrato(TipoInsumoTrato tipoInsumoTrato) {
		this.tipoInsumoTrato = tipoInsumoTrato;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
		InsumoTrato other = (InsumoTrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
	
	
	
	


}
