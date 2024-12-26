package com.universita.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
@Entity
public class Professore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_professore;
	private String nome;
	private String cognome;
	private LocalDate nascita;
	
	@OneToMany(mappedBy = "professore", cascade = CascadeType.ALL)
    private List<Corso> corsi;
	
	public Long getId_professore() {
		return id_professore;
	}
	public void setId_professore(Long id_professore) {
		this.id_professore = id_professore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getNascita() {
		return nascita;
	}
	public void setNascita(LocalDate nascita) {
		this.nascita = nascita;
	}
	
	
}
