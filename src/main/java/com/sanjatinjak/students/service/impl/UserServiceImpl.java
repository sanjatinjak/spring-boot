package com.sanjatinjak.students.service.impl;

import com.sanjatinjak.students.model.User;
import com.sanjatinjak.students.repository.UserRepository;
import com.sanjatinjak.students.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    String username;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String getCurrentUsername() {

        //get username of logged user

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        username = null;

        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserDetails)
                username = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
                username = (String) authentication.getPrincipal();

        //if username is found return it, else return question marks
        try {
            return (username != null ? username : "???");
        } catch (NumberFormatException e) {
            return "Nothing found";
        }
    }

    @Override
    public Long getCurrentId() {

        //find user by username, need it for getting id

        User u = findByUsername(getCurrentUsername());
        Long id = u.getId();

        return id;
    }
}
