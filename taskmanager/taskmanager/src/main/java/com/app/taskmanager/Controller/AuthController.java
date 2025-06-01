package com.app.taskmanager.Controller;


import com.app.taskmanager.Http.Request;
import com.app.taskmanager.Http.Response;
import com.app.taskmanager.Repository.CustomUserDetailsService;
import com.app.taskmanager.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager ;
    @Autowired
    private CustomUserDetailsService userDetailsService ;

    @Autowired
    private JWTUtils jwtUtils ;


    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Request request){

        this.doAuthenticate(request.getUsername(),request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtils.createToken(userDetails);

        Response response = Response.builder()
                .token(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public void doAuthenticate(String username, String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or password");
        }
    }

//    @RequestMapping("/register")
//    public String register(){
//
//    }
}
