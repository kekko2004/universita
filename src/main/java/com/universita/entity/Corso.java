package com.universita.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
@Entity
public class Corso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_corso;
	private String nome;
	private String descrizione;
	private LocalDate inizio;
	private LocalDate fine;
	 @ManyToOne
	 @JoinColumn(name = "id_professore")
	 private Professore professore;

	
	 
	 @ManyToMany(mappedBy = "corsi")
	 private List<Studente> studenti;
	
	public Professore getProfessore() {
		return professore;
	}

	public void setProfessore(Professore professore) {
		this.professore = professore;
	}

	public List<Studente> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

	public Long getId_corso() {
		return id_corso;
	}

	public void setId_corso(Long id_corso) {
		this.id_corso = id_corso;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public LocalDate getInizio() {
		return inizio;
	}
	public void setInizio(LocalDate inizio) {
		this.inizio = inizio;
	}
	public LocalDate getFine() {
		return fine;
	}
	public void setFine(LocalDate fine) {
		this.fine = fine;
	}

	
	
}
