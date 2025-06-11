package com.daniel.tde_backend.config.security;

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
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/perfil/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/perfil").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/perfil/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/perfil/{id}").hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST, "/promotor").hasAnyRole("PROMOTOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/promotor/meus-campeonatos").hasAnyRole("PROMOTOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/promotor/meus-campeonatos/{id}").hasAnyRole("PROMOTOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/promotor/meus-campeonatos/{id}").hasAnyRole("PROMOTOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/promotor/meus-campeonatos/{id}").hasAnyRole("PROMOTOR", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/campeonatos").hasAnyRole("PROMOTOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/campeonatos").authenticated()
                        .requestMatchers(HttpMethod.GET, "/campeonatos/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/campeonatos/{id}").hasAnyRole("PROMOTOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/campeonatos/{id}").hasAnyRole("PROMOTOR", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "equipes").hasAnyRole("JOGADOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/equipes/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/equipes").authenticated()

                        .requestMatchers(HttpMethod.POST, "inscricao/{id}").hasAnyRole("JOGADOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/inscricao/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/inscricao").hasAnyRole("PROMOTOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/inscricao/{id}").hasAnyRole("PROMOTOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/inscricao/{id}").hasAnyRole("JOGADOR", "PROMOTOR", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/ranking").permitAll()
                        .requestMatchers(HttpMethod.POST, "/promocao").hasAnyRole("JOGADOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/status/{idJogador}").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
