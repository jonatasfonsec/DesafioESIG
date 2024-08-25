package com.esig.finanweb.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "pessoa_salario_consolidado")

public class PessoaSalarioConsolidado {
    @Id
    @Column(name = "pessoa_id")
    private int pessoaId;
    
    @Column(name = "nome_pessoa")
    private String nomePessoa;
    
    @Column(name = "nome_cargo")
    private String nomeCargo;
    
    @Column(name = "salario")
    private BigDecimal salario;
	
    
    public PessoaSalarioConsolidado()
    {   	
    }
    
    public PessoaSalarioConsolidado(int pessoaId, String nomePessoa, String nomeCargo, BigDecimal salario) {
		super();
		this.pessoaId = pessoaId;
		this.nomePessoa = nomePessoa;
		this.nomeCargo = nomeCargo;
		this.salario = salario;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
    
}
