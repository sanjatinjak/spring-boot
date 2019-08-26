package com.sanjatinjak.students.controller;

import com.sanjatinjak.students.model.Professor;
import com.sanjatinjak.students.service.ProfessorService;
import com.sanjatinjak.students.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {

    private ProfessorService professorService;
    private UserService userService;

    @Autowired
    public LoginController(ProfessorService professorService, UserService userService) {
        this.professorService = professorService;
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(Model model, String error) {

        if (error != null)
            model.addAttribute("error", "Vaše korisničko ime i lozinka nisu ispravni !");

            return "index";
    }


    @GetMapping("/dashboard")
    public String loginSuccess(Model m) {

        //find professor by id, it returns List of Objects(professors)
        //because id is unique we are expecting one Object in List
        List<Professor> prof = professorService.findById(userService.getCurrentId());

        //get id of the first Object
        Long id = prof.get(0).getId();

        //return Object to view because we need that data for redirecting to other pages
        Professor professor = professorService.get(id);
        m.addAttribute("prof", professor);

        return "profile/dashboard";
    }

}
