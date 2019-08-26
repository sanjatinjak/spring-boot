package com.sanjatinjak.students.service;


import com.sanjatinjak.students.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentsService {

    //finding students that are attending given subject
    @Query("SELECT s FROM student s INNER JOIN kolegij_student ks on s.id=id_studenta where id_kolegija=:id")
    List<Student> findBySubject(@Param("id") Long id);


    @Query("DELETE FROM kolegij_student WHERE id_kolegija=:id")
    int removeStudentBySubject(@Param("id") Long id);

}
