package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //metodo de validacao de formulário da zueira


}
