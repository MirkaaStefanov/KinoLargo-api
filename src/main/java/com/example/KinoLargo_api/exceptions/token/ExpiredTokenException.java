package com.example.KinoLargo_api.exceptions.token;


import com.example.KinoLargo_api.exceptions.common.UnauthorizedException;

public class ExpiredTokenException extends UnauthorizedException {
    public ExpiredTokenException() {
        super("Токенът е изтекъл!");
    }
}
