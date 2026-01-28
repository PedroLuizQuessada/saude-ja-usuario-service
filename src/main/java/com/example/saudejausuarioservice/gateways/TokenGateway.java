package com.example.saudejausuarioservice.gateways;

import com.example.saudejausuarioservice.datasources.TokenDataSource;

public class TokenGateway {
    private final TokenDataSource tokenDataSource;

    public TokenGateway(TokenDataSource tokenDataSource) {
        this.tokenDataSource = tokenDataSource;
    }

    public String getEmail(String token) {
        return tokenDataSource.getEmail(token);
    }
}
