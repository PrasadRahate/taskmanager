package com.app.taskmanager.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HelloController {

    @RequestMapping("/hello")
    public String getHello(){
        return "Hello guys!!!";
    }
}
