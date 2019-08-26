package com.sanjatinjak.students.controller;

import com.sanjatinjak.students.model.Professor;
import com.sanjatinjak.students.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class ProfessorController {

    private ProfessorService professorService;
    private ModelAndView mav;
    private Professor professor;
    private int response;


    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveProfessor(@ModelAttribute("professor") Professor professor, RedirectAttributes redirectAttributes){

        //updating professor data

        response = professorService.save(professor);

        if(response == 0) {
            redirectAttributes.addFlashAttribute("message", "Ažuriranje nije uspjelo");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        } else {

            redirectAttributes.addFlashAttribute("message", "Uspješno ažurirano");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        }

        return  "redirect:/profile/" + professor.getId();
    }

    @RequestMapping(value = "/profile/{id}")
    public ModelAndView profile(@PathVariable(name="id") Long id){

        //returns profile data in form for update

        mav = new ModelAndView("profile/profile");

        professor = professorService.get(id);

        mav.addObject("professor", professor);

        return mav;
    }



}
