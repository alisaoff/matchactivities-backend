package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.dto.RegisterForm;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {

    }

    //metodo de salvar usuario

    public User registerNewUser(RegisterForm newUser) throws Exception {
        //email unico
        if (userRepository.existsUserByEmail(newUser.getEmail())) {
            throw new Exception("Usuário não e unico");
        }

        User user = new User(newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));


        return userRepository.save(user);
    }





    public User findUser(String email){

        return  userRepository.findByEmail(email);
    }



}
