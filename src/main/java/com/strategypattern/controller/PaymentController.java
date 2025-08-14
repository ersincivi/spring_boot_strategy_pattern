package com.strategypattern.controller;

import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResponse;
import com.strategypattern.enums.PaymentMethod;
import com.strategypattern.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public String showPaymentForm(Model model) {
        model.addAttribute("paymentRequest", new PaymentRequest());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "payment-form";
    }

    @PostMapping("/pay")
    public String processPayment(@ModelAttribute PaymentRequest paymentRequest,
            Model model) {
        PaymentResponse response = paymentService.processPayment(paymentRequest);
        model.addAttribute("response", response);
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "payment-result";
    }
}
