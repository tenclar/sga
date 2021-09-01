package br.gov.ac.seap.pga.model;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="cadeialeite")
public class CadeiaLeite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datacad = Calendar.getInstance();
	
	private Date data_info;
	
	@ManyToOne
	@JoinColumn(name="producao_id")
	private Producao producao;
	

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private int lactacao;
	private int solteiras;
	private double producaodiaria;
	private double valorlitro;
	
	
	
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
		
	public Date getData_info() {
		return data_info;
	}
	public void setData_info(Date data_info) {
		this.data_info = data_info;
	}
	public int getLactacao() {
		return lactacao;
	}
	public void setLactacao(int lactacao) {
		this.lactacao = lactacao;
	}
	public int getSolteiras() {
		return solteiras;
	}
	public void setSolteiras(int solteiras) {
		this.solteiras = solteiras;
	}
	public double getProducaodiaria() {
		return producaodiaria;
	}
	public void setProducaodiaria(double producaodiaria) {
		this.producaodiaria = producaodiaria;
	}
	public double getValorlitro() {
		return valorlitro;
	}
	public void setValorlitro(double valorlitro) {
		this.valorlitro = valorlitro;
	}
	public Producao getProducao() {
		return producao;
	}
	public void setProducao(Producao producao) {
		this.producao = producao;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	@Transient
	public double getProducaoMedia(){
		double valor = (this.getLactacao() / this.getProducaodiaria());
		return valor;
	}
	
	@Transient
	public double getProducaoMensal(){
		double valor = (this.getProducaoMedia() * this.getValorlitro()*30);
		return valor;
	}
	
	@Transient
	public double getProducaoHa(){
		double valor =  (this.getProducaodiaria() *  this.producao.getArea().doubleValue());
		return valor;
	}

	
		
	
	

}
