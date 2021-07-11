package com.matchactivities.springbootbackend.model;

import com.sun.istack.NotNull;
import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name ="nome")
    private String name;
    @Column(name ="email")
    private String email;
    @Column(name ="senha")
    private String password;
   // private boolean loggedIn;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
       // this.loggedIn = false;
    }

    public User(){

    }



}
