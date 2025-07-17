package com.example.KinoLargo_api.controllers;

import com.example.KinoLargo_api.services.impl.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exchange")
@AllArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/try")
    public ResponseEntity<Double> exchangeEuroToTRY() {
        return ResponseEntity.ok(exchangeService.getEurToTryRate());
    }

}
