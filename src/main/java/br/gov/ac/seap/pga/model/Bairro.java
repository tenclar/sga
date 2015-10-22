package br.gov.ac.seap.pga.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="bairro")
public class Bairro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datacad = new Date();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@NotNull
	private String nome;
	
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cidade_id")
	private Cidade cidade = new Cidade();
	
	
	
	@ManyToOne
	@JoinColumn(name="regionalmunicipal_id")
	private RegionalMunicipal regionalmunicipal;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	
	
	public RegionalMunicipal getRegionalmunicipal() {
		return regionalmunicipal;
	}

	public void setRegionalmunicipal(RegionalMunicipal regionalmunicipal) {
		this.regionalmunicipal = regionalmunicipal;
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
		Bairro other = (Bairro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	

	
	
	
}