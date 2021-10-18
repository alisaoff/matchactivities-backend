package com.matchactivities.springbootbackend.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchactivities.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final ObjectMapper objectMapper;
    public static final String SECRET = "SEGREDO";
    public static final String HEADER_NAME = "Authorization";
   // public static final Long EXPIRATION_TIME = 1000L*60*30;
   public static final long EXPIRATION_TIME = 3600000; // 60 minutes
    @Autowired
    UserService userService;

    public SecurityConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //permissoes e etc. depois sera necessario a criaçao de roles e outras coisas
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user").permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/login").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/criarTreino").permitAll()
               .antMatchers(HttpMethod.POST, "/criarAgenda").permitAll()
                .antMatchers(HttpMethod.POST, "/api/agenda/criarAgenda").permitAll()
                .antMatchers(HttpMethod.POST, "/api/treinos/criarTreino").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/treinos/deletarTreino").permitAll()
                .antMatchers(HttpMethod.GET, "/api/treinos/listarTreinos").permitAll()

        .anyRequest().authenticated().and()
        .addFilter(new AuthenticationFilter(authenticationManager()))
        .addFilter(new AuthorizationFilter(authenticationManager()));
        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
 
