package com.box.BoxStore.shoebox.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckerController {

    @GetMapping("/check")
    public String gethealth(){
        return "pong" ;

    }
}
