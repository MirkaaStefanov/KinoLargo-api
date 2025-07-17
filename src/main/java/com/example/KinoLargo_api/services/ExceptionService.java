package com.example.KinoLargo_api.services;


import com.example.KinoLargo_api.exceptions.common.ApiException;

public interface ExceptionService {

    void log(ApiException runtimeException);

    void log(RuntimeException runtimeException, int statusCode);
}
