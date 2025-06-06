package com.app.taskmanager.Security;

import com.app.taskmanager.Repository.CustomUserDetailsService;
import com.app.taskmanager.Utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTAuthenticationFilter  extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils ;

    @Autowired
    private CustomUserDetailsService userDetailsService ;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        String token = null;;
        String username = null;

        //extract the token from request header as authorization
        if(header!=null && header.startsWith("Bearer ")){
            token = header.substring(7);
            username = jwtUtils.extractUsername(token);

        }



        //validate the token
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username) ;
            boolean validatetoken = jwtUtils.validateToken(userDetails,token);
            if(validatetoken){

                List<GrantedAuthority> authorities = userDetails.getAuthorities().stream()
                        .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.getAuthority()))
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                System.out.println("Validation failed");
            }
        }

        filterChain.doFilter(request,response);
    }
}
