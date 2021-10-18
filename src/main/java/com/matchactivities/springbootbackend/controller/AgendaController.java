package com.matchactivities.springbootbackend.controller;


import com.matchactivities.springbootbackend.dto.AgendaDTO;
import com.matchactivities.springbootbackend.model.Agenda;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.AgendaRepository;
import com.matchactivities.springbootbackend.service.AgendaService;
import com.matchactivities.springbootbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/agenda")
@RequiredArgsConstructor
public class AgendaController {

    @Autowired
    AgendaService agendaService;


    @Autowired
    AgendaRepository agendaRepository;


    @GetMapping(path ="/listarTodasAgendas")
    public ResponseEntity<List<Agenda>> listAll() {
        return ResponseEntity.ok(agendaRepository.findAll());
    }
    //essa lógica precisa ser melhorada.
    @GetMapping(path ="/listarAgendasEspecificasEspecialista")
    public ResponseEntity<List<Agenda>> listSpecialist() {
        String z;
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        z = principal.getName();
        return ResponseEntity.ok(agendaRepository.findAllByCriadoPor(z));
    }

    @GetMapping(path ="/listarAgendasEspecificas")
    public ResponseEntity<List<Agenda>> list() {
        String z;
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        z = principal.getName();
        return ResponseEntity.ok(agendaRepository.findAllByCriadoPor(z));
    }


    //localhost:8080/api/agenda/email@email.com
    //localhost:8080/api/
    @PostMapping(path = "/{email}")
    public ResponseEntity<String> criar(@PathVariable ("email") String email) throws Exception {


       // try {


            Agenda agenda = agendaService.registerNewAgenda(email);

            return ResponseEntity.ok("Usuário cadastrado");
       /* }
       catch (Exception e){
            return ResponseEntity.badRequest().build();
       }*/



    }


}
