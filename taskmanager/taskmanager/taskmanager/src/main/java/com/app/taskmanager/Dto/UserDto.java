package com.app.taskmanager.Dto;

import com.app.taskmanager.Models.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id ;
    private String username ;

    private String email ;

    private Role role ;

    private String password ;

    public UserDto(String username, String email, String password, Role role) {
    }
}
