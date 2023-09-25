package de.destatis.klausurplaner.security;

import org.h2.engine.UserBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
/* 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;*/

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
     /*
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
            
            .authorizeHttpRequests()
				.anyRequest().authenticated()
			.and()
			.formLogin((form) -> form
				.loginPage("http://localhost:8080/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

            /*
            .csrf().disable()
			.authorizeHttpRequests()
			.anyRequest().authenticated()
			.and()
            .httpBasic();
            */
            /*s
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());
            */
/*
		return http.build();
	}
    
    
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
            .username("admin")
			.password("password")
			.roles("ADMIN")
			.build();
        UserDetails user = User.builder()
            .username("user")
			.password("password")
			.roles("USER")
			.build();

            return new InMemoryUserDetailsManager(admin, user);
    }
    */
}
