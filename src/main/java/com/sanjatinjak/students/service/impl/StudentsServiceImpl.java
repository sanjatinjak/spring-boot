package com.sanjatinjak.students.service.impl;

import com.sanjatinjak.students.model.Student;
import com.sanjatinjak.students.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {

    private JdbcTemplate template;
    private RowMapper<Student> rm;
    private String sql;


    @Autowired
    public StudentsServiceImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Student> findBySubject(Long id) {
        sql = "SELECT * FROM student s INNER JOIN kolegij_student ks on s.id=id_studenta where id_kolegija=?";
        rm = (resultSet, i) -> {
            Student student = new Student(resultSet.getLong("id"),
                    resultSet.getString("ime"),
                    resultSet.getString("prezime"),
                    resultSet.getString("br_indeksa"),
                    resultSet.getInt("godina_studija"));

            return student;
        };

        return template.query(sql, rm, id);
    }

    @Override
    public int removeStudentBySubject(Long id) {
        sql = "DELETE FROM kolegij_student WHERE id_studenta=? ";

        return template.update(sql, id);
    }

}
