    package com.matchactivities.springbootbackend.controller;

    import com.matchactivities.springbootbackend.dto.RegisterForm;
    import com.matchactivities.springbootbackend.model.User;
    import com.matchactivities.springbootbackend.repository.UserRepository;
    import com.matchactivities.springbootbackend.service.UserService;
    import net.bytebuddy.asm.Advice;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.util.List;

        @RestController
        @CrossOrigin(origins = "*", allowedHeaders = "*")
        @RequestMapping("/api/user")
        public class
        UserController {

            @Autowired
            UserService userService;

            @Autowired
            UserRepository userRepository;




                //localhost:8080/api/user/procurarPorEmail?email=
            @GetMapping(path = "/{email}")
            public ResponseEntity<User> procurarPorEmail(@PathVariable ("email") String email){
                try{
                     User user = userService.findUser(email);

                    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
                }

                catch(Exception e){
                    return ResponseEntity.badRequest().build();
                }
            }

            @GetMapping("/listarUsuarios")
            public ResponseEntity<List<User>> list() {
                return ResponseEntity.ok(userRepository.findAll());
            }

            @PostMapping
            public ResponseEntity<String> register(@Valid @RequestBody RegisterForm newUser){

                try {
                    User user = userService.registerNewUser(newUser);
                    return ResponseEntity.ok("Usuário cadastrado");
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.badRequest().build();
                }
            }


        }