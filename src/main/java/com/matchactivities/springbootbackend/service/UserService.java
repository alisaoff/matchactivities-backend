package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.model.RegisterForm;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //metodo de validacao de formulário da zueira


    //metodo de salvar usuario

    public User registerNewUser(RegisterForm newUser){

    User user = new User(newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
       return userRepository.save(user) ;
    }
}
