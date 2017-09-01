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
@Table(name="preparoarea")
public class PreparoArea implements Serializable {
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
		
	private Date data_info;
	
	@ManyToOne
	@JoinColumn(name="producao_id")
	private Producao producao;
	
	@ManyToOne
	@JoinColumn(name="servico_id")
	private ServicoMecanizacao servicomecanizacao;
	
		
	@NotNull	 
	private BigDecimal hta;
	
	@NotNull
	@Column(name = "area", precision = 4, scale = 2) 
	private BigDecimal area;
	
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

	public Date getData_info() {
		return data_info;
	}

	public void setData_info(Date data_info) {
		this.data_info = data_info;
	}

	public Producao getProducao() {
		return producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}

	public ServicoMecanizacao getServicomecanizacao() {
		return servicomecanizacao;
	}

	public void setServicomecanizacao(ServicoMecanizacao servicomecanizacao) {
		this.servicomecanizacao = servicomecanizacao;
	}



	public BigDecimal getHta() {
		return hta;
	}
	public void setHta(BigDecimal hta) {
		this.hta = hta;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
	


}
