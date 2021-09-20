package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.model.RegisterForm;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.UserRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserService implements UserDetailsService {

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
        if (userRepository.existsUserByEmail(newUser.getEmail())){
            throw new Exception("Usuário não e unico");
        }


        User user = new User(newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(user);
    }



    //override do loadUserByUsername do security para seguir MINHAS regras :)
    //talvez seja interessante fazer separar userDetailsService de userService (que no caso seriam as regras de serviço
    //referentes ao user do matchActivities... e nao da interface

    //pedir para o fellipe mandar "name":"blabla, "password":"blabla,
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            System.out.println("Usuario nao encontrado");
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }



}
