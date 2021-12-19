package com.matchactivities.springbootbackend.config;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchactivities.springbootbackend.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.util.UrlPathHelper;

import static java.lang.Integer.valueOf;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final static UrlPathHelper urlPathHelper = new UrlPathHelper();

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }



    //magica
    @Autowired
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {

            User applicationUser = new ObjectMapper().readValue(req.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(applicationUser.getUsername(),applicationUser.getPassword());
            setDetails(req, token);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(applicationUser.getEmail(),
                            applicationUser.getPassword(), Collections.emptyList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {




        Date exp = new Date(System.currentTimeMillis() + SecurityConfiguration.EXPIRATION_TIME);
        Claims claims = Jwts.claims().setSubject(auth.getName());
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SecurityConfiguration.SECRET).setExpiration(exp).compact();
        res.addHeader("token", token);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        rootNode.put("token", (token));

        ObjectNode childNode2 = mapper.createObjectNode();
        childNode2.put("name", auth.getName());

        rootNode.set("user", childNode2);

        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        res.getWriter().write(jsonString);
        System.out.println("deu certo :)");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        logger.debug("failed authentication while attempting to access "
                + urlPathHelper.getPathWithinApplication((HttpServletRequest) request));

        //Add more descriptive message
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication Failed");
    }

    public int getIdusuario(String token) {
        Claims claims =  Jwts.parser().setSigningKey(SecurityConfiguration.SECRET).parseClaimsJws(token).getBody();
        return valueOf(Integer.parseInt(claims.getSubject()));
    }
}

