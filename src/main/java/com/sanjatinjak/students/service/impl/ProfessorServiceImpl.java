package com.sanjatinjak.students.service.impl;

import com.sanjatinjak.students.model.Professor;
import com.sanjatinjak.students.model.Subject;
import com.sanjatinjak.students.model.User;
import com.sanjatinjak.students.repository.ProfessorRepository;
import com.sanjatinjak.students.service.ProfessorService;
import com.sanjatinjak.students.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private JdbcTemplate template;
    private ProfessorRepository professorRepository;
    private Professor professorUpdate;

    private UserService userService;
    private RowMapper<Professor> rm;
    private RowMapper<Subject> rms;
    private String sql;

    @Autowired
    public ProfessorServiceImpl(JdbcTemplate template, ProfessorRepository professorRepository, UserService userService) {
        this.template = template;
        this.professorRepository = professorRepository;
        this.userService = userService;
    }

    @Override
    public User findByUsername(String username) {
        return userService.findByUsername(userService.getCurrentUsername());
    }

    @Override
    public List<Professor> findById(Long id) {

        sql = "select * from profesor where user_id=?";
        rm = (resultSet, i) -> {
            Professor professor = new Professor(resultSet.getLong("id"),
                    resultSet.getString("ime"),
                    resultSet.getString("prezime"),
                    resultSet.getDate("datum_rodenja"),
                    resultSet.getString("email"),
                    resultSet.getString("br_mobitela"),
                    resultSet.getLong("user_id")
            );

            return professor;
        };

        return template.query(sql, rm, id);
    }

    @Override
    public List<Subject> getSubject(Long id) {

        sql = "SELECT * FROM kolegij k INNER JOIN profesor_kolegij pk ON k.id=id_kolegija WHERE id_profesora=?";
        rms = (resultSet, i) -> {
            Subject subject = new Subject(resultSet.getLong("id"),
                    resultSet.getString("naziv_kolegija"),
                    resultSet.getInt("semestar_izvodenja")
            );

            return subject;
        };


        return template.query(sql, rms, id);
    }

    @Override
    public int save(Professor professor) {

        professorUpdate = professorRepository.save(professor);

        //just to check if update run successfully
        if(professor.equals(professorUpdate)){
            return 0;
        } else return 1;

    }

    @Override
    public Professor get(long id) {
        return professorRepository.findById(id).get();
    }
}
