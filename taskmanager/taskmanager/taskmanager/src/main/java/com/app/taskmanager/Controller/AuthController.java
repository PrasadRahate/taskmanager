package com.app.taskmanager.Controller;


import com.app.taskmanager.Http.Request;
import com.app.taskmanager.Http.Response;
import com.app.taskmanager.Models.User;
import com.app.taskmanager.Repository.CustomUserDetailsService;
import com.app.taskmanager.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder ;


    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Request request){
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String storedHashedPassword = user.getPassword();  // This should be the bcrypt hash stored in DB
        System.out.println(storedHashedPassword);
        System.out.println(passwordEncoder.encode(request.getPassword()));
        boolean isMatch = passwordEncoder.matches(request.getPassword(),storedHashedPassword);

        System.out.println("Password matches? " + isMatch);
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
