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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     
    private CustomUserDetailsService userDetailsService;

    private static final String API_URL_PATTERN = "api/auth/**";


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


        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));


        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(antMatcher("/api/auth/**")).permitAll()
                                .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                                .requestMatchers(antMatcher("/**")).permitAll()
                                .anyRequest().authenticated()
    
            ); 

		return http.build();
	}
    
    
    
    @Bean
    public AuthenticationManager authentificationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
     * Spring Security: BCrypt Passwort Hashing Methode:
     * 
     * Besteht aus dem Passwort-String (bis zu 72 bytes), einem numerischen 'cost'-Parameter (standardmäßig 12),
     * und einem 16 byte 'salt'-Wert (Wert aus zufälligen Zeichen).
     * Dieser Prozess wird in mehreren Runden (2^cost Runden) durch den Eksblowfish Algorithmus modifiziert.
     * 
     * Beispiel: input = 'abc123xyz', cost-Wert von 12
     * 
     * $2<a/b/x/y>$[cost]$[22 character salt][31 character hash]
     * 
     * 
     * $2a$12$R9h/cIPz0gi.URNNX3kh2OPST9/PgBkqquzi.Ss7KIUgO2t0jWMUW
     * \__/\/ \____________________/\_____________________________/
     *Alg Cost      Salt                        Hash
     *
     * ($2a$ = Hash Algorithmus identifikator [bcrypt])
     * 
     * Quelle: https://en.wikipedia.org/wiki/Bcrypt
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
