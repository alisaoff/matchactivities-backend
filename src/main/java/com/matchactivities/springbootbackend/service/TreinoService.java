package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.dto.TreinoDTO;
import com.matchactivities.springbootbackend.model.Agenda;
import com.matchactivities.springbootbackend.model.Treino;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.AgendaRepository;
import com.matchactivities.springbootbackend.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TreinoService {

    @Autowired
    TreinoRepository treinoRepository;
    @Autowired
    AgendaRepository agendaRepository;
    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    public TreinoService() {

    }

    public Treino registerNewTreino(TreinoDTO newTreino) {
        //validacoes?

        Agenda agenda = agendaRepository.findById(newTreino.getIdAgenda());

        Treino treino = Treino.builder()
                .agenda(agenda)
                .horario(newTreino.getHorario())
                .data(newTreino.getData())
                .atividade(newTreino.getAtividade())
                .descricao(newTreino.getDescricao())
                .estado(false).build();
        System.out.println("treino salvo ;)");

        return treinoRepository.save(treino);


    }

    public void deletarTreino(Long id) {
        treinoRepository.deleteById(id);
    }

    /*
    //TODO
    public Treino alterarTreino(Treino treino) {

        return treino;

    } */




}
