package com.matchactivities.springbootbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor

@Table
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome")
    private String nome;

    @Column(name = "criadoPor")
    private String criadoPor;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agenda")
    private List<Treino> treinos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
