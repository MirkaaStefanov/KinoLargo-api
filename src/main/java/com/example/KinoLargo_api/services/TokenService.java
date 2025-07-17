package com.example.KinoLargo_api.services;



import com.example.KinoLargo_api.enums.TokenType;
import com.example.KinoLargo_api.models.dto.auth.AuthenticationResponse;
import com.example.KinoLargo_api.models.entity.Token;
import com.example.KinoLargo_api.models.entity.User;

import java.util.List;

public interface TokenService {
    Token findByToken(String jwt);

    List<Token> findByUser(User user);

    void saveToken(User user, String jwtToken, TokenType tokenType);

    void revokeToken(Token token);

    void revokeAllUserTokens(User user);

    void logoutToken(String jwt);

    AuthenticationResponse generateAuthResponse(User user);

    void createVerificationToken(User user, String token);

    void clearVerificationTokensByUser(User user);
}
