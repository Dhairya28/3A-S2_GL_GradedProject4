package com.gl.employeemanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.employeemanagement.serviceImpl.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers(HttpMethod.POST, "/api/user", "/api/role").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/employees/employeesList", "/api/employees/{employee_id}")
		.hasAnyAuthority("USER", "ADMIN").antMatchers(HttpMethod.POST, "/api/employees/add")
		.hasAuthority("ADMIN").antMatchers(HttpMethod.PUT, "/api/employees/update").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/employees/delete/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/employees/search/*", "/api/employees/sort/*")
		.hasAnyAuthority("USER", "ADMIN").antMatchers("/swagger-ui.html").hasAuthority("ADMIN").anyRequest()
		.authenticated().and().httpBasic().and().formLogin().and().logout().logoutSuccessUrl("/login")
		.permitAll().and().cors().and().csrf().disable();
    }
    
}
