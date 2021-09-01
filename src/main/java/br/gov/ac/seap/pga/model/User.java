package br.gov.ac.seap.pga.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetails;


/**
 * The persistent class for the user database table.
 * 
 */

//@NamedEntityGraph(name = "User.detail",
//attributeNodes = @NamedAttributeNode("authorities"))
@Entity
@Table(name="user")
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date datacad = new Date();

	@NotNull
	private String email;
	
	
	private boolean enabled = true;

	@Column(name="full_name")
	private String fullName;
	
	private String cpf;

	@NotNull
	private String username;

	@ManyToOne
	@JoinColumn(name="setor_id")	
	private Setor setor = new Setor();

	@NotNull
	private String password;
	
	

	@Column(name="phone_number")
	private String phoneNumber;
	


	//bi-directional many-to-many association to Permission
	
//	@JoinTable(
//			name="user_authority"
//			, joinColumns={
//				@JoinColumn(name="user_id")
//				}
//			, inverseJoinColumns={
//				@JoinColumn(name="authority_id")
//				}
//			)
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Authority> authorities= new LinkedList<Authority>();

	
	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDatacad() {
		return this.datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFullName() {
		return this.fullName;
	}

	public void setfullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}


	@Override
	public boolean isEnabled() {
	
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	
	@Override
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}


	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

	


	
	
}