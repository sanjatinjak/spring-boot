package com.sanjatinjak.students.service.impl;


import com.sanjatinjak.students.model.User;
import com.sanjatinjak.students.repository.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@ComponentScan
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;


    public UserDetailsServiceImpl(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return new UserDetailsImpl(user);
    }
}
