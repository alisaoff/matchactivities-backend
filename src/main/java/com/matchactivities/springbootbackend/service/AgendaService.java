package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.model.Agenda;
import com.matchactivities.springbootbackend.model.Treino;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    UserService userService;

    @Autowired
    AgendaRepository agendaRepository;

    public Agenda registerNewAgenda(String email) {
        String z;
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        z = principal.getName();

        User user = userService.findUser(email);
        //depois adicionar confirmação

        String name = user.getName();
        String username = user.getUsername();

        List<Treino> treinos = new ArrayList<>();
        Agenda agenda = Agenda.builder().criadoPor(z).nome(name).user(user).treinos(treinos).isActive(true).build();

        return agendaRepository.save(agenda);

    }

}