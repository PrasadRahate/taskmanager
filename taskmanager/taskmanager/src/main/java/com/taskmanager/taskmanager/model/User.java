package com.taskmanager.taskmanager.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id ;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email ;

    @Column(name="password")
    private String password ;
}
