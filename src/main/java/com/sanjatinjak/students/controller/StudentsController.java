package com.sanjatinjak.students.controller;

import com.sanjatinjak.students.model.Professor;
import com.sanjatinjak.students.model.Student;
import com.sanjatinjak.students.model.Subject;
import com.sanjatinjak.students.repository.SubjectRepository;
import com.sanjatinjak.students.service.ProfessorService;
import com.sanjatinjak.students.service.StudentsService;
import com.sanjatinjak.students.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentsController {

    private StudentsService studentsService;
    private ProfessorService professorService;
    private UserService userService;
    private SubjectRepository subjectRepository;

    private List<Professor> prof;
    private Professor professor;
    private List<Subject> sub;

    private Long idOfUser;
    private Long idOfSubject;
    private List<Student> students;
    private List<Student> studentsAttendingSubject;
    private Subject chosenSubject;

    private ModelAndView mav;

    @Autowired
    public StudentsController(StudentsService studentsService, ProfessorService professorService, UserService userService, SubjectRepository subjectRepository) {
        this.studentsService = studentsService;
        this.professorService = professorService;
        this.userService = userService;
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping(value = "/showStudents", method = RequestMethod.GET)
    public void index(Model md, HttpServletResponse response){

        //to find current logged professor
        prof = professorService.findById(userService.getCurrentId());

        //find that professor and return Object
        professor = prof.get(0);

        //list of subjects find by professor id in they table
        sub = professorService.getSubject(professor.getId());



        students = new ArrayList<>();
        studentsAttendingSubject = new ArrayList<>();

        //first we need to loop through returned list of subjects of current logged professor
        //to get ids
        //after that we are looking for students attending that subject and in another loop
        //we are adding Objects in list to pass it to view and show data

        for (Subject s: sub) {
            idOfSubject = s.getId();
            studentsAttendingSubject = studentsService.findBySubject(idOfSubject);

            for(Student student : studentsAttendingSubject){
                students.add(student);
            }
        }

        md.addAttribute("students", students);
        md.addAttribute("sub", sub);

        if(sub.isEmpty()) {
            try {
                response.sendRedirect("/showStudents");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.sendRedirect("/showStudents/" + sub.get(0).getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value = "/showStudents/{id}")
    public ModelAndView filterBySubject(@PathVariable Long id){

        //get current logged professor
        idOfUser = userService.getCurrentId();
        professor = professorService.get(idOfUser);

        //get all subjects of that professor
        sub = professorService.getSubject(professor.getId());

        //get only the requested one by PathVariable id
        chosenSubject = subjectRepository.findById(id).get();

        //find all students on that subject
        students = studentsService.findBySubject(id);

        //add all needed variables to mav and return view
        mav = new ModelAndView("students/showStudents");

        mav.addObject("students", students);
        mav.addObject("sub", sub);
        mav.addObject("chosenSubject", chosenSubject);

        return mav;
    }

    @RequestMapping(value = "/remove/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeStudentFromSubject(@PathVariable Long id, HttpServletResponse response){

        //removing student from that Subject
        //PathVariable is referred to Student's id

        studentsService.removeStudentBySubject(id);

            try {
                response.sendRedirect("/showStudents");
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


}
