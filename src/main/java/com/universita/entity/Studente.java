package com.universita.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
@Entity
public class Studente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matricola;
	private String nome;
	private String cognome;
	private LocalDate nascita;
	
    @ManyToMany
    @JoinTable(
            name = "studenti_corsi",
            joinColumns = @JoinColumn(name = "matricola"),
            inverseJoinColumns = @JoinColumn(name = "id_corso"))
    private List<Corso> corsi;
	
	public List<Corso> getCorsi() {
		return corsi;
	}
	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}
	public Long getMatricola() {
		return matricola;
	}
	public void setMatricola(Long matricola) {
		this.matricola = matricola;
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