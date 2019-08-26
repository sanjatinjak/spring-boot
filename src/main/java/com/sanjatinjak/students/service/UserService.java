package com.sanjatinjak.students.service;

import com.sanjatinjak.students.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void save(User user);

    User findByUsername(String username);

    String getCurrentUsername();

    Long getCurrentId();
}
