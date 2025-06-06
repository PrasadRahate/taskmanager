package com.app.taskmanager.Http;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {

    private String token ;
    private String username;
}
