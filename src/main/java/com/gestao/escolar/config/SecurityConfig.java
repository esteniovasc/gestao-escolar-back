package com.gestao.escolar.config;

import com.gestao.escolar.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = 
                    new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(inMemoryUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsService(){
        return new UserDetailsServiceImpl();
    }

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(
                (authorize) -> authorize
                        .requestMatchers("/usuario/cadastro").permitAll()
                        .requestMatchers("/usuario/salvar").permitAll()
                        .requestMatchers("/styles/**", "/assets/**").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/aluno")
                        .permitAll()
        );
        return http.build();
    }
}
