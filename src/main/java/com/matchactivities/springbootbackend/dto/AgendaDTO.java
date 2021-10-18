package com.matchactivities.springbootbackend.dto;

import com.matchactivities.springbootbackend.model.User;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AgendaDTO {

    @NotBlank
    private User user;
    //
    private String criadoPor;
    //SecurityContextHolder.getUsername();


}
