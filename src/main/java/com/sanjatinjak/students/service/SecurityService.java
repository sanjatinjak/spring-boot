package com.sanjatinjak.students.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
