package com.app.taskmanager.Models;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message="Username is required!!")
    private String username ;
    @Column(name="password")
    @NotBlank
    private String password ;
    @Email(message = "Email should be valid !!")
    @Column(name="email")
    @NotBlank(message = "Email is required")
    private String email ;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role ;


    @OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();




}
