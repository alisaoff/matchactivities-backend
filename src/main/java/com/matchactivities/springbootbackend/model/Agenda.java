package com.matchactivities.springbootbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "treinos")
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "nome")
    private String nome;

    @Column(name = "criadoPor")
    private String criadoPor;


    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="id")
    private Set<Treino> treinos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
