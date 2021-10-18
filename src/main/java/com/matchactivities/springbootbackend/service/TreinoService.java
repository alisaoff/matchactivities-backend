package com.matchactivities.springbootbackend.service;

import com.matchactivities.springbootbackend.model.Treino;
import com.matchactivities.springbootbackend.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TreinoService {

    @Autowired
    TreinoRepository treinoRepository;


    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    public TreinoService() {

    }

    public Treino registerNewTreino(Treino newTreino) {

        return treinoRepository.save(newTreino);

    }

    public Treino alterarTreino(Treino treino) {

        return treino;

    }

    public void deletarTreino(Long id) {
        treinoRepository.deleteById(id);
    }


}
