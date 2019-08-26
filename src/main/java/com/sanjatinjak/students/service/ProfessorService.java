package com.sanjatinjak.students.service;

import com.sanjatinjak.students.model.Professor;
import com.sanjatinjak.students.model.Subject;
import com.sanjatinjak.students.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessorService {

    //it helps us to find current logged user because we can find it
    //by username
    @Query("select u from users u where u.username=:username")
    User findByUsername(@Param("username") String username);

    //find professor by id, current logged user -> professor
    @Query("select p from profesor p where p.user_id=:id")
    List<Professor> findById(@Param("id") Long id);

    //get all subjects of logged professor
    @Query("select k from kolegij k inner join profesor_kolegij pk on k.id=id_kolegija where id_profesora=:id")
    List<Subject> getSubject(@Param("id") Long id);

    //updating data
    int save(Professor professor);

    //finds Object by id
    Professor get(long id);

}
