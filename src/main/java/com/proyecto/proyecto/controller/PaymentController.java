package com.proyecto.proyecto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.dto.PaymentResponse;
import com.proyecto.proyecto.service.MercadoPagoService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final MercadoPagoService service;

    public PaymentController(MercadoPagoService service) {
        this.service = service;
    }

    @PostMapping
    public PaymentResponse pay(@RequestParam Double amount) throws Exception {
        String url = service.createPayment(amount, "Compra de computadora");
        return new PaymentResponse(url);
    }
}
