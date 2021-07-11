package com.matchactivities.springbootbackend.controller;

import com.matchactivities.springbootbackend.model.RegisterForm;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
	    public ResponseEntity<List<User>> list(){
        	        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody @Valid RegisterForm newUser, BindingResult result) {
/*
        if (result.hasErrors()) {


            return ResponseEntity.badRequest().body("Erros no formulário");

        }*/

        User user = new User(newUser.getName(), newUser.getEmail(),newUser.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok("Usuário cadastrado");
    }
}

        //valida se ja existe o email, se a senha valida

        // enfia no bdd






/*
//metodo da zueira
    @GetMapping("users")
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }
}
*/