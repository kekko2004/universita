package com.universita.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.universita.entity.Professore;
import com.universita.repository.CorsoRepository;
import com.universita.repository.ProfessoreRepository;
@Controller
@RequestMapping("/professori")
public class ProfessoreController {
	@Autowired 
	CorsoRepository corsoRepository;
	@Autowired
	ProfessoreRepository professoreRepository;
	@GetMapping
    public String listaProfessori(Model model){
        List<Professore> professori = professoreRepository.findAll();
        model.addAttribute("professori", professori);
        return "lista_professori";
    }

    @GetMapping("/nuovo")
    public String nuovoProfessoreForm(Model model){
        model.addAttribute("professore", new Professore());
        return "form_professore";
    }

    @PostMapping("/nuovo")
    public String salvaNuovoProfessore(@ModelAttribute Professore professore){
        professoreRepository.save(professore);
        return "redirect:/professori";
    }
    @GetMapping("/modifica/{id}")
    public String mostraModificaProfessore(@PathVariable Long id, Model model) {
    	Professore professore = professoreRepository.findById(id).orElse(null);
    	
    	model.addAttribute("professore", professore);
    	return "form_professore";
    }
    @PostMapping("/modifica")
    public String salvaModificheProfessore(@ModelAttribute Professore professore) {
    	professoreRepository.save(professore);
        return "redirect:/corsi";
    }
    @GetMapping("/elimina/{id}")
    public String eliminaProfessore(@PathVariable Long id) {
    	professoreRepository.deleteById(id);
        return "redirect:/professori";
    }
}
