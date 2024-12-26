package com.universita.controller;

import java.util.*;

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
import com.universita.entity.Professore;
import com.universita.repository.CorsoRepository;
import com.universita.repository.ProfessoreRepository;
import com.universita.repository.StudenteRepository;
@Controller
@RequestMapping("/corsi")
public class CorsoController{
	
	@Autowired
	ProfessoreRepository professoreRepository;
	@Autowired
	StudenteRepository studenteRepository;
	@Autowired 
	CorsoRepository corsoRepository;
	
	@GetMapping
	 public String listaCorso(Model model, 
	            @RequestParam(required = false) String ricercaCorso,
	            @RequestParam(required = false) String ricercaProfessore
	          
	            ) {
	        
	        List<Corso> corsi = corsoRepository.findAll();
	        
	         if (ricercaCorso != null && !ricercaCorso.isEmpty()) {
	            corsi = corsoRepository.findByNomeContaining(ricercaCorso);
	        } else if(ricercaProfessore != null && !ricercaProfessore.isEmpty()){
	            corsi = corsoRepository.findByProfessoreNomeLike(ricercaProfessore);
	        }
	        model.addAttribute("corsi", corsi);
	        return "lista_corsi";
	    }
	 @GetMapping("/nuovo")
	    public String nuovoCorsoForm(Model model) {
	        List<Professore> professori = professoreRepository.findAll();
	        model.addAttribute("professori", professori);
	        model.addAttribute("corso", new Corso());
	        return "form_corso";
	    }

	    @PostMapping("/nuovo")
	    public String salvaNuovoCorso(@ModelAttribute Corso corso) {
	        Professore professore = professoreRepository.findById(corso.getProfessore().getId_professore()).orElse(null);
	        corso.setProfessore(professore);
	        corsoRepository.save(corso);
	        return "redirect:/corsi";
	    }

	    @GetMapping("/modifica/{id}")
	    public String modificaCorsoForm(@PathVariable Long id, Model model) {
	        Corso corso = corsoRepository.findById(id).orElse(null);
	        List<Professore> professori = professoreRepository.findAll();
	        
	        model.addAttribute("professori", professori);
	        model.addAttribute("corso", corso);
	        return "form_corso";
	    }

	    @PostMapping("/modifica")
	    public String salvaModificheCorso(@ModelAttribute Corso corso) {
	        /*Autore autore = autoreRepository.findById(libro.getAutore().getId()).orElse(null);
	        libro.setAutore(autore);*/
	        corsoRepository.save(corso);
	        return "redirect:/corsi";
	    }

	    @GetMapping("/elimina/{id}")
	    public String eliminaCorso(@PathVariable Long id) {
	        corsoRepository.deleteById(id);
	        return "redirect:/corsi";
	    }
	
}
