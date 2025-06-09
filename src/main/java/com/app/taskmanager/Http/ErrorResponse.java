package com.app.taskmanager.Http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timeStamp ;
    private String message ;
    private String details ;

    public ErrorResponse(String message,String details){
        this.timeStamp = LocalDateTime.now();
        this.message = message ;
        this.details = details ;
    }

}
