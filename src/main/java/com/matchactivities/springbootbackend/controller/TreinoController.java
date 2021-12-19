package com.matchactivities.springbootbackend.controller;

import com.matchactivities.springbootbackend.dto.RegisterForm;
import com.matchactivities.springbootbackend.dto.TreinoDTO;
import com.matchactivities.springbootbackend.model.Treino;
import com.matchactivities.springbootbackend.model.User;
import com.matchactivities.springbootbackend.repository.TreinoRepository;
import com.matchactivities.springbootbackend.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/treino")
@RequiredArgsConstructor
public class TreinoController {

    @Autowired
    TreinoService treinoService;

    @Autowired
    TreinoRepository treinoRepository;

    //funciona
    @GetMapping
    public ResponseEntity<List<Treino>> list(Long agendaId) {

        return ResponseEntity.ok(treinoRepository.findAll());
    }

    //funciona
    @PostMapping
    public ResponseEntity<String> criar(@Valid @RequestBody TreinoDTO newTreino) {

        try{
            Treino treino = treinoService.registerNewTreino(newTreino);
            return ResponseEntity.ok("Treino cadastrado");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }


    }

    //Para realizar o delete, o caminho é: localhost:8080/api/treinos/deletarTreino?id=[ID QUE QUISER](Aqui vai o ID que quiser deletar)
    //funciona
    @DeleteMapping(path = "/deletarTreino")
    public ResponseEntity<String> deletarTreino(@RequestParam Long id) {
        treinoService.deletarTreino(id);

        return ResponseEntity.ok("Usuario deletado");

    }

    /*//precisa mexer
    @PostMapping(path = "/alterarTreino")
    public ResponseEntity<Treino> alterar(@RequestBody Treino treino) {
        return new ResponseEntity<Treino>(treinoService.alterarTreino(treino), HttpStatus.OK);
        return ResponseEntity.ok("OK");
    }
    */

}
