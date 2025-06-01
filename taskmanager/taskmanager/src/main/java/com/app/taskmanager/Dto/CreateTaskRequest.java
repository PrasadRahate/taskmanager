package com.app.taskmanager.Dto;

import com.app.taskmanager.Models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Statement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
    private String title;
    private String description;
    private Status status ;
    // Getters and setters
}