package com.example.paymentservice.controllers;

import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public void processPayment(@RequestParam Long orderId, @RequestParam double amount)
    {
        paymentService.processPayment(orderId, amount);
    }
}

