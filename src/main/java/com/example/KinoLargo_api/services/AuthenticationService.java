package com.example.KinoLargo_api.services;


import com.example.KinoLargo_api.models.dto.auth.AuthenticationRequest;
import com.example.KinoLargo_api.models.dto.auth.AuthenticationResponse;
import com.example.KinoLargo_api.models.dto.auth.PublicUserDTO;
import com.example.KinoLargo_api.models.dto.auth.RegisterRequest;
import com.example.KinoLargo_api.models.entity.User;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse refreshToken(String refreshToken) throws IOException;

    PublicUserDTO me(
            String jwtToken
    );

    void resetPassword(String token, String newPassword);

    void confirmRegistration(String verificationToken);

    User forgotPassword(String email);
}
