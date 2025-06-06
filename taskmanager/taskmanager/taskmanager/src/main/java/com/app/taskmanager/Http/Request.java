package com.app.taskmanager.Http;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {

    private String username ;
    private String password ;
}
