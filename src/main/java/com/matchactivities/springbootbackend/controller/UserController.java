package com.matchactivities.springbootbackend.controller;

import com.matchactivities.springbootbackend.model.RegisterForm;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody RegisterForm newUser) {


        // kkkk nao tem metodo de validacao ainda
        User user = new User(newUser.getName(), newUser.getEmail(), newUser.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok("usuario cadastrado");
    }


}