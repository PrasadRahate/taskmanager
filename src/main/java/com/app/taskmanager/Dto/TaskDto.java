package com.app.taskmanager.Dto;


import com.app.taskmanager.Models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {


    private int id ;
    @NotBlank(message = "Title is required")
    private String title ;
    @Size(min = 10 ,max=500,message = "Description must be under 500 characters")
    private String description ;
    private Status status ;


}
