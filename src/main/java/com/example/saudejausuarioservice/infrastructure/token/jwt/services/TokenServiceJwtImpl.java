package com.example.saudejausuarioservice.infrastructure.token.jwt.services;

import com.example.saudejausuarioservice.datasources.TokenDataSource;
import com.example.saudejausuarioservice.infrastructure.exceptions.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@Profile("jwt")
public class TokenServiceJwtImpl implements TokenDataSource {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public String getEmail(String token) {
        Jwt jwt = decodeToken(token);
        return  jwt.getSubject();
    }

    private Jwt decodeToken(String token) {
        try {
            return jwtDecoder.decode(token.replace("Bearer ", ""));
        }
        catch (Exception e) {
            throw new InvalidJwtException();
        }
    }
}
