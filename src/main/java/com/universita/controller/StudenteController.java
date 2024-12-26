package com.universita.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.universita.entity.Corso;
import com.universita.entity.Studente;
import com.universita.repository.CorsoRepository;
import com.universita.repository.ProfessoreRepository;
import com.universita.repository.StudenteRepository;
@Controller
@RequestMapping("/studenti")
public class StudenteController {
	@Autowired
	StudenteRepository studenteRepository;
	@Autowired 
	CorsoRepository corsoRepository;
	@Autowired
	ProfessoreRepository professoreRepository;
	@GetMapping
    public String listaStudenti(Model model,
    		@RequestParam(required = false) String ricercaByCorso,
            @RequestParam(required = false) String dataNascita){
		List<Studente> studenti = studenteRepository.findAll();
		
		if(dataNascita != null && !dataNascita.isEmpty()) {
        	LocalDate dataNascitaParsed = LocalDate.parse(dataNascita);
        	System.out.println(dataNascitaParsed);
        	studenti = 	studenteRepository.findByNascita(dataNascitaParsed);
        }else if (ricercaByCorso != null && !ricercaByCorso.isEmpty()) {
            studenti = studenteRepository.findByCorsiIn(corsoRepository.findByNomeContaining(ricercaByCorso));
        } 
       
        model.addAttribute("studenti", studenti);
        return "lista_studenti";
    }

    @GetMapping("/nuovo")
    public String nuovoStudenteForm(Model model){
    	List<Corso> listaCorsi = corsoRepository.findAll();
        model.addAttribute("listaCorsi", listaCorsi);
        model.addAttribute("studente", new Studente());
        return "form_studente";
    }

    @PostMapping("/nuovo")
    public String salvaNuovoStudente(@ModelAttribute Studente studente){
    	
    	studenteRepository.save(studente);
        return "redirect:/studenti";
    }
    @GetMapping("/modifica/{id}")
    public String mostraModificaStudente(@PathVariable Long id, Model model) {
    	Studente studente = studenteRepository.findById(id).orElse(null);
    	List<Corso> listaCorsi = corsoRepository.findAll();
        model.addAttribute("listaCorsi", listaCorsi);
    	model.addAttribute("studente", studente);
    	return "form_studente";
    }
    @PostMapping("/modifica")
    public String salvaModificheStudente(@ModelAttribute Studente studente) {
    	studenteRepository.save(studente);
        return "redirect:/Studenti";
    }
    @GetMapping("/elimina/{id}")
    public String eliminaStudente(@PathVariable Long id) {
    	studenteRepository.deleteById(id);
        return "redirect:/studenti";
    }
}
