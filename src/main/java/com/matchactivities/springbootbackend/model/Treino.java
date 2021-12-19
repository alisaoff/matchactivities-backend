package com.matchactivities.springbootbackend.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@Table(name = "treinos")


@ToString

@NoArgsConstructor

//Builder estava dando falha
//foi por causa do lombok :))
@Getter
@Setter

public class Treino {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="agenda_id")
    @NotNull
    private Agenda agenda;

    @Column(name = "atividade")
    private String atividade;

    @Column(name = "descricao")
    private String descricao;

    //depois volta pra data
    @Column(name = "data")
    private String data;

    //Mudar para date depois
    @Column(name = "horario")
    private String horario;

    @Column(name = "estaTerminado")
    private Boolean isFinished;


}