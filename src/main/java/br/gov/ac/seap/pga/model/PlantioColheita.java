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
@Table(name="plantio_colheita")
public class PlantioColheita implements Serializable {
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
	
	
		
	@Temporal(TemporalType.DATE)
	private Date dataPlantio;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataColheita;
	
	@ManyToOne
	@JoinColumn(name="producao_id")
	private Producao producao;
	
	@NotNull	 
	private String culturaPlantio;
	
	@NotNull	 
	private String culturaColheita;
	
	@NotNull	 
	private String ciclo;
	
	@NotNull	 
	private String variedade;
	
	@NotNull	 
	private String fabricante;
	
	
	@NotNull	 
	private String umidade;
	
	@NotNull	 
	private String epoca;
	@NotNull	 
	private String estagio;
	
	@NotNull	 
	private String destino;
	
	
	
	
	@NotNull
	@Column(name = "areaplantio", precision = 10, scale = 2) 
	private BigDecimal areaPlantio;
	
	@NotNull
	@Column(name = "areacoplheita", precision = 10, scale = 2) 
	private BigDecimal areaColheita;
	
	@NotNull
	@Column(name = "valor", precision = 10, scale = 2) 
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

	public Date getDataPlantio() {
		return dataPlantio;
	}

	public void setDataPlantio(Date dataPlantio) {
		this.dataPlantio = dataPlantio;
	}

	public Date getDataColheita() {
		return dataColheita;
	}

	public void setDataColheita(Date dataColheita) {
		this.dataColheita = dataColheita;
	}

	public Producao getProducao() {
		return producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}

	public String getCulturaPlantio() {
		return culturaPlantio;
	}

	public void setCulturaPlantio(String culturaPlantio) {
		this.culturaPlantio = culturaPlantio;
	}

	public String getCulturaColheita() {
		return culturaColheita;
	}

	public void setCulturaColheita(String culturaColheita) {
		this.culturaColheita = culturaColheita;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getVariedade() {
		return variedade;
	}

	public void setVariedade(String variedade) {
		this.variedade = variedade;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getUmidade() {
		return umidade;
	}

	public void setUmidade(String umidade) {
		this.umidade = umidade;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public BigDecimal getAreaPlantio() {
		return areaPlantio;
	}

	public void setAreaPlantio(BigDecimal areaPlantio) {
		this.areaPlantio = areaPlantio;
	}

	public BigDecimal getAreaColheita() {
		return areaColheita;
	}

	public void setAreaColheita(BigDecimal areaColheita) {
		this.areaColheita = areaColheita;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	
	
	public String getEpoca() {
		return epoca;
	}

	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}

	
	
	public String getEstagio() {
		return estagio;
	}

	public void setEstagio(String estagio) {
		this.estagio = estagio;
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
		PlantioColheita other = (PlantioColheita) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	


}
