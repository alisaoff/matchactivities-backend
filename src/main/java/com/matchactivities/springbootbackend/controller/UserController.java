package com.matchactivities.springbootbackend.controller;

import com.matchactivities.springbootbackend.model.RegisterForm;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.UserRepository;
import com.matchactivities.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(userRepository.findAll());
    }


    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody RegisterForm newUser) {

        User user = userService.registerNewUser(newUser);


        return ResponseEntity.ok("Usuário cadastrado");
    }




}