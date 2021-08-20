/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gateways.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Rawan.Ahmed
 */
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter{

    private UserDetailsService userDetailsService;
    

            
    public WebSecConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    
    protected void configure(HttpSecurity http) throws Exception {   
        http.cors().and().csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
            .antMatchers("/Gateways-API/**").permitAll();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods(HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name());
            }

        };
    }
        
}
