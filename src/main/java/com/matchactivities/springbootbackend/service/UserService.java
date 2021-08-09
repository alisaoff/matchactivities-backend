package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.model.RegisterForm;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User registerNewUser(RegisterForm newUser) {

        User user = new User(newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(user);
    }

    //override do loadUserByUsername do security para seguir MINHAS regras :)
    //talvez seja interessante fazer separar userDetailsService de userService (que no caso seriam as regras de serviço
    //referentes ao user do matchActivities... e nao da interface
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);

        if (user == null) throw new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Collections.emptyList());
    }

    public User loadUserByUsernameDois(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);

        if (user == null) throw new UsernameNotFoundException(username);

        return user;
    }
}
