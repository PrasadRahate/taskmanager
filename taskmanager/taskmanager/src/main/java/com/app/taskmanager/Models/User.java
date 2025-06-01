package com.app.taskmanager.Models;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String username ;
    @Column(name="password")
    private String password ;
    @Column(name="email")
    private String email ;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role ;


    @OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();




}
