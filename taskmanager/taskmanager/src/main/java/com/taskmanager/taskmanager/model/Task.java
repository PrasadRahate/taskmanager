package com.taskmanager.taskmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;


    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;

    @ManyToOne
    private User user ;


}
