package com.matchactivities.springbootbackend.model;


import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.Valid;
public class RegisterForm

{
    @Size(min=2, max=30)
    private String name;
    @NotNull @Email
    private String email;
    @NotNull @Size(min=3, max= 20)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
