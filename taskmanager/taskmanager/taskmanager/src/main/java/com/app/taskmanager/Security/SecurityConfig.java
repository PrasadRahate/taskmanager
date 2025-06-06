package com.app.taskmanager.Security;

import com.app.taskmanager.Repository.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JWTAuthenticationFilter filter  ;

    @Autowired
    private CustomUserDetailsService userDetailsService ;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(csrf -> csrf.disable()).cors(cors->cors.disable())
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/auth/login")
                                .permitAll()
                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/swagger-ui.html",
                                        "/webjars/**",
                                        "/actuator/**"
                                ).permitAll()
                                        .requestMatchers("/api/auth/new").permitAll()
                                .requestMatchers("/api/auth/**").authenticated()
                                .requestMatchers("/tasks/**").authenticated().requestMatchers("/home/**").authenticated().anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build() ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,PasswordEncoder encoder) throws Exception {
        AuthenticationManagerBuilder authbuild = http.getSharedObject(AuthenticationManagerBuilder.class);
        authbuild.userDetailsService(userDetailsService).passwordEncoder(encoder);
        return authbuild.build() ;
    }
}
