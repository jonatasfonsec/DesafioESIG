package com.esig.finanweb.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nome")
    private String nome;
    
    @OneToMany(mappedBy = "cargo")
    private List<CargoVencimento> cargoVencimentos;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
    public List<CargoVencimento> getCargoVencimentos() {
        return cargoVencimentos;
    }

    public void setCargoVencimentos(List<CargoVencimento> cargoVencimentos) {
        this.cargoVencimentos = cargoVencimentos;
    }
  
}
