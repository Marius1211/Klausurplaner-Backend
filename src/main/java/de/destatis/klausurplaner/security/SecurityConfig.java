package de.destatis.klausurplaner.security;

import org.h2.engine.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     
    private CustomUserDetailsService userDetailsService;

    private static final String API_URL_PATTERN = "api/auth/**";

    /*
   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("ADMIN");
    }*/

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {

/*
        http.csrf(csrfConfigurer ->
                csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN),
                        PathRequest.toH2Console()));
*/
        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        /*
        http
            .csrf(csrf -> csrf.disable())
            .authorizeRequests()
            .requestMatchers(antMatcher("/api/auth/**")).permitAll()
            .requestMatchers(antMatcher("/h2-console/**")).permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
*/
         http.csrf().disable()
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(antMatcher("/api/auth/**")).permitAll()
                                .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                                .anyRequest().authenticated()
    
            ); 




            /*
            http.authorizeHttpRequests((authorize) -> authorize

                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin((form) -> form
                                .loginPage("http://localhost:8080/login")
                                .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .httpBasic();
            */

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

    // Dies erstellt ein BCryptPasswordEncoder damit man einfach die Daten Verschlüsseln zu können
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
