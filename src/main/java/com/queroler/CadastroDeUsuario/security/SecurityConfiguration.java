package com.queroler.CadastroDeUsuario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecuritFilter securitFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(autorize -> autorize
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET,"/usuarios").hasRole("LEITOR")
                        .requestMatchers(HttpMethod.GET,"/notificacoes").hasRole("LEITOR")
                        .requestMatchers(HttpMethod.PATCH,"/notificacoes").hasRole("LEITOR")
                        .requestMatchers(HttpMethod.PATCH,"/usuarios").hasRole("LEITOR")
                        .requestMatchers(HttpMethod.DELETE,"/usuarios").hasRole("LEITOR")
                        .requestMatchers(HttpMethod.POST, "/administradores").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/documentos").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/documentos").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/administradores/senha").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/livros").hasRole("LEITOR")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securitFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}