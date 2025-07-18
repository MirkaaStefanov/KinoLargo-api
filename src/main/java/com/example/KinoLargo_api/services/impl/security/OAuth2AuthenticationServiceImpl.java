package com.example.KinoLargo_api.services.impl.security;

import com.example.KinoLargo_api.config.FrontendConfig;
import com.example.KinoLargo_api.enums.Provider;
import com.example.KinoLargo_api.exceptions.token.InvalidTokenException;
import com.example.KinoLargo_api.models.dto.auth.AuthenticationResponse;
import com.example.KinoLargo_api.models.dto.auth.OAuth2UserInfoDTO;
import com.example.KinoLargo_api.models.entity.User;
import com.example.KinoLargo_api.services.OAuth2AuthenticationService;
import com.example.KinoLargo_api.services.TokenService;
import com.example.KinoLargo_api.services.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of the OAuth2AuthenticationService interface responsible for processing OAuth2 user authentication.
 * This services handles the post-login process, including token generation and cookie attachment.
 */
@Service
@RequiredArgsConstructor
public class OAuth2AuthenticationServiceImpl implements OAuth2AuthenticationService {
    private final List<String> SCOPES = List.of("email", "profile", "openid");
    private final FrontendConfig frontendConfig;
    private final WebClient userInfoClient;
    private final UserService userService;
    private final TokenService tokenService;


    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret;

    @Override
    public String getOAuthGoogleLoginUrl() {
        return new GoogleAuthorizationCodeRequestUrl(clientId, frontendConfig.getOauth2RedirectUrl(), SCOPES).build();
    }

    @Override
    public AuthenticationResponse processOAuthGoogleLogin(String code) {
        // Authenticate user with Google
        String token = authorizeWithGoogle(code);
        OAuth2UserInfoDTO oAuth2UserInfoDTO = getUserInfoFromGoogleToken(token);
        oAuth2UserInfoDTO.setProvider(Provider.GOOGLE);

        // Process OAuth2 user and retrieve associated user entity
        User user = userService.processOAuthUser(oAuth2UserInfoDTO);
        tokenService.revokeAllUserTokens(user);

        return tokenService.generateAuthResponse(user);
    }

    private OAuth2UserInfoDTO getUserInfoFromGoogleToken(String googleToken) {
        return userInfoClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth2/v3/userinfo")
                        .queryParam("access_token", googleToken)
                        .build())
                .retrieve()
                .bodyToMono(OAuth2UserInfoDTO.class)
                .block();
    }

    private String authorizeWithGoogle(String code) {
        try {
            return new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    frontendConfig.getOauth2RedirectUrl())
                    .execute()
                    .getAccessToken();
        } catch (IOException e) {
            throw new InvalidTokenException();
        }
    }
}
