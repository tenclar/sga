

package br.gov.ac.seap.pga.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.gov.ac.seap.pga.enumerator.EnumTipoPessoa;

@Entity
@Table(name = "produtor")
public class Produtor implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String cpf;
	private String rg;
	private String orgexp;
	private String genero;
	private String email;
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoPessoa tipopessoa = EnumTipoPessoa.FISICA;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date datacad = new Date();
	

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date datanasc;
	

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user = new User();	

	@NotNull
	private String name;

	@NotNull
	@Size(min = 10, max = 12, message = "10-12 Numbers")
	@Digits(fraction = 0, integer = 12, message = "Not valid")
	@Column(name = "phone_number")
	private String phoneNumber;


	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco = new Endereco();		
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="produtor", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<Propriedade> propriedades = new ArrayList<Propriedade>() ;
	
	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgexp() {
		return orgexp;
	}

	public void setOrgexp(String orgexp) {
		this.orgexp = orgexp;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public EnumTipoPessoa getTipopessoa() {
		return tipopessoa;
	}

	public void setTipopessoa(EnumTipoPessoa tipopessoa) {
		this.tipopessoa = tipopessoa;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Propriedade> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<Propriedade> propriedades) {
		this.propriedades = propriedades;
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
		Produtor other = (Produtor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

	
}
