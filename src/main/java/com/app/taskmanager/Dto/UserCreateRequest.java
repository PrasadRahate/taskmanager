package com.app.taskmanager.Dto;


import com.app.taskmanager.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    private String username ;
    private String password ;
    private String email ;
    private Role role ;


}
