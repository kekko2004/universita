package com.universita.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universita.entity.*;
public interface StudenteRepository extends JpaRepository<Studente, Long>{
	List<Studente> findByCorsiIn(List<Corso> corsi);
	 List<Studente> findByNascita(LocalDate nascita);
	List<Studente> getStudenteByCorsi(Corso corsi);
}
