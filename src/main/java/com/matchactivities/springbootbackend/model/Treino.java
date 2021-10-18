package com.matchactivities.springbootbackend.model;

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
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="agenda_id")
    private Agenda agenda;

    @Column(name = "atividade")
    private String atividade;

    @Column(name = "descricao")
    private String descricao;

    //depois volta pra data
    @Column(name = "data")
    private String data;


   // @Column(name = "duracao")
   // private Date duracao;

    //Mudar para date depois depois
    @Column(name = "horario")
    private String horario;

    @Column(name = "estado")
    private Boolean estado;


}
