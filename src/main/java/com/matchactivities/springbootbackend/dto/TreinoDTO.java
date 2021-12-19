package com.matchactivities.springbootbackend.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
@Data
public class TreinoDTO {

    private int idAgenda;

    private String atividade;


    private String descricao;

    //depois volta pra data
    @Column(name = "data")
    private String data;


    //Mudar para date depois

    private String horario;

    public TreinoDTO(int idAgenda, String atividade, String descricao, String data, String horario) {
        this.idAgenda = idAgenda;
        this.atividade = atividade;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public String getAtividade() {
        return atividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }
}