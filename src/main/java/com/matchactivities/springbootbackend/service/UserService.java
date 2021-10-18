package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.dto.RegisterForm;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



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
        if (userRepository.existsUserByEmail(newUser.getEmail())) {
            throw new Exception("Usuário não e unico");
        }

        System.out.println(newUser.getPassword());
        User user = new User(newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(user);
    }




    public User loadUserByEmail(String username) throws UsernameNotFoundException {
        /* User user = userRepository.findByEmail(username);

       if (userRepository.existsUserByEmail(username)) {
            throw new UsernameNotFoundException("Usuário não e unico");
        }
        if (user == null) {
            System.out.println("Usuario nao encontrado");
            throw new UsernameNotFoundException(username);
        }*/

        return userRepository.findByEmail(username);
    }

    public User findUser(String email){

        return  userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            System.out.println("Usuario nao encontrado");
            throw new UsernameNotFoundException(username);
        }

        //return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
        return user;
    }


}
