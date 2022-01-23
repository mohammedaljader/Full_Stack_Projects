package org.project.joboffers.Security.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/login")){
            filterChain.doFilter(request, response);
        }
        else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = getToken(authorizationHeader);
                    Algorithm algorithm = getAlgorithm();
                    JWTVerifier verifier = getJwtVerifier(algorithm);
                    DecodedJWT decodedJWT = getDecodedJWT(token, verifier);
                    String username = decodedJWT.getSubject();
                    Collection<SimpleGrantedAuthority> authorities = getSimpleGrantedAuthorities(decodedJWT);
                    UsernamePasswordAuthenticationToken authenticationToken = getUsernamePasswordAuthenticationToken(username, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }
                catch (Exception exception){
                    log.error("Error logging in : {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),error);
                }
            }
            else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String username, Collection<SimpleGrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                null, authorities);
        return authenticationToken;
    }

    private Collection<SimpleGrantedAuthority> getSimpleGrantedAuthorities(DecodedJWT decodedJWT) {
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

    private DecodedJWT getDecodedJWT(String token, JWTVerifier verifier) {
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    private JWTVerifier getJwtVerifier(Algorithm algorithm) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier;
    }

    private Algorithm getAlgorithm() {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return algorithm;
    }

    private String getToken(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        return token;
    }
}
