package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.dto.AgendaDTO;
import com.matchactivities.springbootbackend.model.Agenda;
import com.matchactivities.springbootbackend.model.Treino;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    UserService userService;

    @Autowired
    AgendaRepository agendaRepository;

/*
    public AgendaService(AgendaRepository agendaRepository) {NAO PRECISA INSTANCIAR AQUI, TOLO!
        this.agendaRepository = agendaRepository;NAO PRECISA INSTANCIAR AQUI, TOLO!
    }NAO PRECISA INSTANCIAR AQUI, TOLO!
NAO PRECISA INSTANCIAR AQUI, TOLO!
    public AgendaService() {NAO PRECISA INSTANCIAR AQUI, TOLO!
NAO PRECISA INSTANCIAR AQUI, TOLO!
    }
    NAO PRECISA INSTANCIAR AQUI, TOLO!
    NAO PRECISA INSTANCIAR AQUI, TOLO!*/



    public Agenda registerNewAgenda(String email){
        String z;
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        z = principal.getName();




        User user = userService.findUser(email);
        //depois adicionar confirmação


        String name = user.getName();
       String username = user.getUsername();

        List<Treino> treinos = new ArrayList<>();
        Agenda agenda = Agenda.builder().criadoPor(z).nome(name).treinos(treinos).user(user).build();

        return agendaRepository.save(agenda);

    }



}